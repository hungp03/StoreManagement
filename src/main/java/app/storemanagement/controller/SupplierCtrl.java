package app.storemanagement.controller;

/**
 *
 * @author Hung Pham
 */
import app.storemanagement.model.SupplierModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SupplierCtrl implements BaseController<SupplierModel> {

    private Connection conn;

    public SupplierCtrl() {
    }

    public SupplierCtrl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(SupplierModel supplier) {
        String sql = "INSERT INTO Supplier (Supplier_ID, Supplier_Name, Address, Phone, Email)"
                + "SELECT ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM Supplier WHERE Supplier_Name = ? OR Phone = ? OR Email = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplier.getId());
            stmt.setString(2, supplier.getFullName());
            stmt.setString(3, supplier.getAddress());
            stmt.setString(4, supplier.getPhone());
            stmt.setString(5, supplier.getEmail());
            stmt.setString(6, supplier.getFullName());
            stmt.setString(7, supplier.getPhone());
            stmt.setString(8, supplier.getEmail());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Tên,số điện thoại hoặc email đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(SupplierModel supplier) {
        String sql = "UPDATE Supplier SET Supplier_Name = ?, Address = ?, Phone = ?, Email = ? WHERE Supplier_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplier.getFullName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getPhone());
            stmt.setString(4, supplier.getEmail());
            stmt.setInt(5, supplier.getId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhà cung cấp để cập nhật", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                JOptionPane.showMessageDialog(null, "Tên, số điện thoại hoặc email nhà cung cấp đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    @Override
    public boolean delete(SupplierModel supplier) {
        String sql = "DELETE FROM Supplier WHERE Supplier_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplier.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa nhà cung cấp vì đã có sản phẩm liên quan đến nhà cung cấp này", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public List<SupplierModel> getSuppliers(String keyword, String searchMethod, String sortMethod) {
        List<SupplierModel> suppliers = new ArrayList<>();
        String searchQuery = generateSearchQuery(keyword, searchMethod);
        String sql = generateSortQuery(sortMethod, searchQuery);

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Tạo một đối tượng SupplierModel từ ResultSet
                SupplierModel supplier = new SupplierModel(rs.getInt("Supplier_ID"), rs.getString("Supplier_Name"), rs.getString("Address"), rs.getString("Phone"), rs.getString("Email"));
                supplier.setProductQty(rs.getInt("Sosanpham"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    private String generateSearchQuery(String keyword, String searchMethod) {
        String tmp = "";
        if (!keyword.trim().isEmpty()) {
            switch (searchMethod) {
                case "Mã NCC" ->
                    tmp = " WHERE Supplier.Supplier_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên NCC" ->
                    tmp = " WHERE Supplier.Supplier_Name LIKE N'%" + keyword.trim() + "%' COLLATE SQL_Latin1_General_CP1253_CI_AI ";
                case "Số điện thoại" ->
                    tmp = " WHERE Supplier.Phone LIKE N'%" + keyword.trim() + "%' ";
                default -> {
                }
            }
        }
        return """
               select Supplier.*, count(Product_ID) as Sosanpham from
               Supplier left join Product on Product.Supplier_ID = Supplier.Supplier_ID
               """ + tmp
                + "group by Supplier.Supplier_ID,Supplier.Supplier_Name, Supplier.Address, Supplier.Email, Supplier.Phone";
    }

    private String generateSortQuery(String sortMethod, String searchQuery) {
        return switch (sortMethod) {
            case "Mã NCC" ->
                searchQuery + " ORDER BY Supplier_ID";
            case "Tên NCC" ->
                searchQuery + " ORDER BY Supplier_Name";
            default ->
                searchQuery;
        };
    }
}
