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
    
    public static String displayQuery(String sortMethod) {
        String query = "select * from Category";
        if (sortMethod.equals("Mã phân loại")) {
            query += " ORDER BY Category_ID";
        } else if (sortMethod.equals("Tên phân loại")) {
            query += " ORDER BY Category_Name";
        }
        return query;
    }
    
    public static String searchQuery(String keyword, String sortMethod) {
        String tmp = "";
        switch (sortMethod) {
            case "Mã phân loại" -> tmp += "Category_ID";
            case "Tên phân loại" -> tmp += "Category_Name";
            default -> {
            }
        }
        String query = "SELECT * FROM Category WHERE Category_Name LIKE N'%" + keyword + "%' COLLATE Vietnamese_CI_AI ORDER BY " + tmp;
        return query;
    }
    
}
