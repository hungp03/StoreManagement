package app.storemanagement.controller;

/**
 *
 * @author AnTran
 */
import app.storemanagement.model.CustomerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String sql = "INSERT INTO Customer (Customer_ID, Full_Name, Address, Phone, Email)"
                + "SELECT ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM Customer WHERE Phone = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getEmail());
            stmt.setString(6, customer.getPhone());
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
        String sql = "UPDATE Customer SET Full_Name=?, Address=?, Phone=?, Email=?"
                + "WHERE Customer_ID=? AND NOT EXISTS (SELECT 1 FROM Customer WHERE Phone = ? AND Customer_ID <> ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(6, customer.getId());
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setInt(7, customer.getId());
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

    public String generateQuery(String sortMethod, String keyword, String searchMethod) {
        String tmp = "";
        if (keyword.trim().isEmpty() == false) {
            switch (searchMethod) {
                case "Mã KH" ->
                    tmp = " WHERE Customer_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên KH" ->
                    tmp = " WHERE Full_Name LIKE N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI ";
                case "Số điện thoại" ->
                    tmp = " WHERE Phone LIKE N'%" + keyword.trim() + "%' ";
                default -> {
                }
            }
        }
        String query = "select * from Customer" + tmp;
        switch (sortMethod) {
            case "Mã KH" ->
                query += " ORDER BY Customer_ID";
            case "Tên KH" ->
                query += " ORDER BY Full_Name";
            default -> {
            }
        }
        return query;
    }
}
