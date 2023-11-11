package app.storemanagement.controller;

import app.storemanagement.model.BaseEntity;
import app.storemanagement.model.Connection.DBConnection;
import app.storemanagement.model.ProductModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class ProductCtl implements BaseController<ProductModel> {

    private Connection conn;

    public ProductCtl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addProduct(ProductModel product) {
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

}
