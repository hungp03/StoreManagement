package app.storemanagement.controller;

import app.storemanagement.model.Connection.DBConnection;
import app.storemanagement.model.ProductTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class SellCtrl {

    private Connection conn;

    public SellCtrl(Connection conn) {
        this.conn = conn;
    }

    public SellCtrl() {
    }

    public List<ProductTableModel> displayAndSearch(String keyword, String searchMethod) {
        List<ProductTableModel> products = new ArrayList<>();
        String sql = generateSearchQuery(keyword, searchMethod);
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Tạo một đối tượng ProductDisplayModel từ ResultSet
                ProductTableModel product = new ProductTableModel(
                        rs.getInt("Product_ID"),
                        rs.getString("Product_Name"),
                        rs.getString("Category_Name"),
                        rs.getDouble("Unit_Price"),
                        rs.getInt("Quantity_In_Stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private String generateSearchQuery(String keyword, String searchMethod) {
        String tmp = "";
        if (!keyword.trim().isEmpty()) {
            switch (searchMethod) {
                case "Mã SP" ->
                    tmp = " AND Product_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên SP" ->
                    tmp = " AND Product_Name LIKE N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI ";
                default -> {
                }
            }
        }
        return """
               SELECT Product_ID, Product_Name, Unit_Price, Category.Category_Name,Quantity_In_Stock 
               FROM Product inner join Category on Product.Category_ID = Category.Category_ID
               WHERE Expiry_Date > GETDATE() and Quantity_In_Stock > 0""" + tmp;
    }

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

    public double totalAmount(List<ProductTableModel> cart) {
        double total_amount = 0;
        for (ProductTableModel product : cart) {
            total_amount += product.getUnitprice() * product.getQty();
        }
        return total_amount;
    }

    public boolean addInvoice(int invoiceId, int customerId, int uid, double amount, double customerCash, double paid, String payment_Method, List<ProductTableModel> cart) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            addInvoiceToDB(conn, invoiceId, customerId, uid, amount, customerCash, paid, payment_Method);
            addProductsToDB(conn, invoiceId, cart);
            conn.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void addInvoiceToDB(Connection conn, int invoiceId, int customerId, int uid, double amount, double customerCash, double paid, String payment_Method) throws SQLException {
        String sql = "INSERT INTO Invoice (Invoice_ID, Date, Total_Amount, Customer_Cash, Return_Money , Payment_Method, Employee_ID, Customer_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, invoiceId);
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Ngày hiện tại
            stmt.setDouble(3, amount); // Tổng tiền
            stmt.setDouble(4, customerCash); // Tổng tiền
            stmt.setDouble(5, paid); // Tổng tiền
            stmt.setString(6, payment_Method); // Phương thức thanh toán
            stmt.setInt(7, uid); // ID nhân viên
            stmt.setInt(8, customerId); // ID khách hàng
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProductsToDB(Connection conn, int invoiceId, List<ProductTableModel> cart) throws SQLException {
        String sql = "INSERT INTO Contain (Invoice_ID, Product_ID, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt2 = conn.prepareStatement(sql)) {
            for (ProductTableModel product : cart) {
                stmt2.setInt(1, invoiceId);
                stmt2.setInt(2, product.getId());
                stmt2.setInt(3, product.getQty());
                stmt2.executeUpdate();
                updateProductQuantity(product.getId(), product.getQty(), "reduce");
            }
        }
    }
}
