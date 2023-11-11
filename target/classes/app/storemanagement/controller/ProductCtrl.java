package app.storemanagement.controller;

import app.storemanagement.model.ProductModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class ProductCtrl implements BaseController<ProductModel> {

    private Connection conn;

    public ProductCtrl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(ProductModel product) {
        String sql = "INSERT INTO Product (Product_ID, Product_Name, Category_ID, Unit_Price, Quantity_In_Stock, Description, Manufacture_Date, Expiry_Date, Entry_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getCategoryId());
            stmt.setDouble(4, product.getUnitPrice());
            stmt.setInt(5, product.getQuantityInStock());
            stmt.setString(6, product.getDescription());
            stmt.setDate(7, new java.sql.Date(product.getManufactureDate().getTime()));
            stmt.setDate(8, new java.sql.Date(product.getExpiryDate().getTime()));
            stmt.setDate(9, new java.sql.Date(product.getEntryDate().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(ProductModel product) {
        return true;
    }

    @Override
    public boolean delete(ProductModel product) {
        return true;
    }
}
