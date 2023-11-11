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
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
