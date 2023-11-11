package app.storemanagement.controller;

import app.storemanagement.model.Connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hung Pham
 */
public class Category {

    private int key = 0;
    private boolean isRowSelected = false;

    public static void addCategory(int categoryID, String categoryName) {
        String sql = "INSERT INTO Category (CategoryName) VALUES (?,?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryID);
            pstmt.setString(2, categoryName);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
