package app.storemanagement.controller;

/**
 *
 * @author AnTran
 */
import app.storemanagement.model.CustomerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CustomerCtrl implements BaseController<CustomerModel> {

    private Connection conn;

    public CustomerCtrl() {
    }

    public CustomerCtrl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(CustomerModel customer) {
        String sql = "INSERT INTO Customer (Customer_ID, Full_Name, Address, Phone)"
                + "SELECT ?, ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM Customer WHERE Phone = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getPhone());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(CustomerModel customer) {
        String sql = "UPDATE Customer SET Full_Name=?, Address=?, Phone=?"
                + "WHERE Customer_ID=? AND NOT EXISTS (SELECT 1 FROM Customer WHERE Phone = ? AND Customer_ID <> ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(4, customer.getId());
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(5, customer.getPhone());
            stmt.setInt(6, customer.getId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean delete(CustomerModel customer) {
        String sql = "DELETE FROM Customer WHERE Customer_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customer.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa khách hàng vì đã có hóa đơn liên quan đến khách hàng này", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public List<CustomerModel> getCustomers(String keyword, String searchMethod, String sortMethod) {
        List<CustomerModel> customers = new ArrayList<>();
        String searchQuery = generateSearchQuery(keyword, searchMethod);
        String sql = generateSortQuery(sortMethod, searchQuery);

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Tạo một đối tượng CustomerModel từ ResultSet
                CustomerModel customer = new CustomerModel(rs.getInt("Customer_ID"), rs.getString("Full_Name"), rs.getString("Address"), rs.getString("Phone"));
                customer.setInvoiceQty(rs.getInt("Sodonhang"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    private String generateSearchQuery(String keyword, String searchMethod) {
        String tmp = "";
        if (!keyword.trim().isEmpty()) {
            switch (searchMethod) {
                case "Mã KH" ->
                    tmp = " WHERE Customer.Customer_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên KH" ->
                    tmp = " WHERE Customer.Full_Name LIKE N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI ";
                case "Số điện thoại" ->
                    tmp = " WHERE Customer.Phone LIKE N'%" + keyword.trim() + "%' ";
                default -> {
                }
            }
        }
        return """
               select Customer.*, count(Invoice_ID) as Sodonhang from
               Customer left join Invoice on Customer.Customer_ID = Invoice.Customer_ID """
                + tmp
                + " group by Customer.Customer_ID, Customer.Address, Customer.Full_Name, Customer.Phone";
    }

    private String generateSortQuery(String sortMethod, String searchQuery) {
        return switch (sortMethod) {
            case "Mã KH" ->
                searchQuery + " ORDER BY Customer_ID";
            case "Tên KH" ->
                searchQuery + " ORDER BY Full_Name";
            default ->
                searchQuery;
        };
    }
}
