package app.storemanagement.controller;

/**
 *
 * @author Hung Pham
 */
import app.storemanagement.model.SupplierModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public String generateQuery(String sortMethod, String keyword, String searchMethod) {
        String tmp = "";
        if (keyword.trim().isEmpty() == false) {
            switch (searchMethod) {
                case "Mã NCC" ->
                    tmp = " WHERE Supplier_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên NCC" ->
                    tmp = " WHERE Supplier_Name LIKE N'%" + keyword.trim() + "%' COLLATE SQL_Latin1_General_CP1253_CI_AI ";
                case "Số điện thoại" ->
                    tmp = " WHERE Phone LIKE N'%" + keyword.trim() + "%' ";
                default -> {
                }
            }
        }
        String query = "select * from Supplier" + tmp;
        switch (sortMethod) {
            case "Mã NCC" ->
                query += " ORDER BY Supplier_ID";
            case "Tên NCC" ->
                query += " ORDER BY Supplier_Name";
            default -> {
            }
        }
        return query;
    }
}
