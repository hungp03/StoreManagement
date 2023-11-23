package app.storemanagement.controller;

import app.storemanagement.model.CategoryModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class CategoryCtrl implements BaseController<CategoryModel>{

    private Connection conn;

    public CategoryCtrl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public boolean add(CategoryModel category){
        String sql = "INSERT INTO Category VALUES (?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category.getId());
            stmt.setString(2, category.getCategoryName());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    @Override
    public boolean update(CategoryModel category) {
        String sql = "update Category set Category_Name=? where Category_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(2, category.getId());
            stmt.setString(1, category.getCategoryName());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean delete(CategoryModel category) {
        String sql = "delete from Category where Category_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")){
                JOptionPane.showMessageDialog(null, "Không thể xóa danh mục do đã có sản phẩm liên kết đến danh mục này" , "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }
    
    public static String displayQuery(String sortMethod, String keyword) {
        String query = "select * from Category where Category_Name like N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI";
        if (sortMethod.equals("Mã phân loại")) {
            query += " ORDER BY Category_ID";
        } else if (sortMethod.equals("Tên phân loại")) {
            query += " ORDER BY Category_Name";
        }
        return query;
    }
    
}
