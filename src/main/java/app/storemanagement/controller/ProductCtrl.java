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

    public ProductCtrl() {
    }
    
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
        String sql = "UPDATE Product SET Product_Name = ?, Category_ID = ?, Unit_Price = ?, Quantity_In_Stock = ?, Description = ?, Manufacture_Date = ?, Expiry_Date = ?, Entry_Date = ?"
                + "WHERE Product_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(9, product.getId());
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, product.getQuantityInStock());
            stmt.setString(5, product.getDescription());
            stmt.setDate(6, new java.sql.Date(product.getManufactureDate().getTime()));
            stmt.setDate(7, new java.sql.Date(product.getExpiryDate().getTime()));
            stmt.setDate(8, new java.sql.Date(product.getEntryDate().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean delete(ProductModel product) {
        String sql = "delete from Product where Product_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.print(e.getErrorCode());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public String generateQuery(String sortMethod, String keyword, String searchMethod) {
        String tmp = "";
        if (keyword.trim().isEmpty() == false) {
            switch (searchMethod) {
                case "Mã" ->
                    tmp = " WHERE Product_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên" ->
                    tmp = " WHERE Product_Name LIKE N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI ";
                case "Phân loại" ->
                    tmp = " WHERE Category_Name LIKE N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI ";
                default -> {
                }
            }
        }
        String query = "select Product_ID, Product_Name, Category.Category_Name, Entry_Date from Product inner join Category on Product.Category_ID = Category.Category_ID"
                + tmp;
        switch (sortMethod) {
            case "Mã SP" ->
                query += " ORDER BY Product_ID";
            case "Tên SP" ->
                query += " ORDER BY Product_Name";
            case "Ngày nhập hàng" ->
                query += " ORDER BY Entry_Date";
            default -> {
            }
        }
        return query;
    }
}
