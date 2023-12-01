package app.storemanagement.controller;

import app.storemanagement.model.Connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class SellCtrl {

    public String generateQuery(String keyword, String searchMethod) {
        String query = """
                       SELECT Product_ID, Product_Name, Unit_Price, Quantity_In_Stock FROM Product
                       WHERE Expiry_Date > GETDATE() and Quantity_In_Stock > 0
                       """;
        if (!keyword.trim().isEmpty()) {
            switch (searchMethod) {
                case "Mã SP" ->
                    query += " and Product_ID LIKE N'%" + keyword.trim() + "%'";
                case "Tên SP" ->
                    query += " and Product_Name LIKE N'%" + keyword + "%' COLLATE Vietnamese_CI_AI";
                default -> {
                }
            }
        }
        return query;
    }

//    public void updateProductQuantity(int id, int pqty, String type) throws IllegalArgumentException {
//
//        String sql = "";
//        switch (type) {
//            // Giảm số lượng sản phẩm trong cơ sở dữ liệu
//            case "reduce" ->
//                sql = "UPDATE Product SET Quantity_In_Stock = Quantity_In_Stock - ? WHERE Product_ID = ?";
//            // Tăng số lượng sản phẩm trong cơ sở dữ liệu
//            case "increase" ->
//                sql = "UPDATE Product SET Quantity_In_Stock = Quantity_In_Stock + ? WHERE Product_ID = ?";
//            default ->
//                throw new IllegalArgumentException("Type must be either 'reduce' or 'increase'");
//        }
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, pqty);
//            stmt.setInt(2, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public void updateProductQuantity(int id, int pqty, String type) throws IllegalArgumentException {
        String sql = "";
        switch (type) {
            case "reduce" ->
                sql = "UPDATE Product SET Quantity_In_Stock = Quantity_In_Stock - ? WHERE Product_ID = ?";
            case "increase" ->
                sql = "UPDATE Product SET Quantity_In_Stock = Quantity_In_Stock + ? WHERE Product_ID = ?";
            default ->
                throw new IllegalArgumentException("Type must be either 'reduce' or 'increase'");
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Bắt đầu một transaction mới
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, pqty);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }

            // Kết thúc transaction
            conn.commit();
        } catch (SQLException e) {
            System.err.print("Transaction is being rolled back");
        }
    }

}
