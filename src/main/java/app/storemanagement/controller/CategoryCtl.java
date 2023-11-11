package app.storemanagement.controller;

import app.storemanagement.model.Connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class CategoryCtl {
    public static boolean addCategory(int categoryID, String categoryName) {
        String sql = "INSERT INTO Category VALUES (?,?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryID);
            pstmt.setString(2, categoryName);
            pstmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
