package app.storemanagement.controller;

import app.storemanagement.model.CategoryModel;
import app.storemanagement.model.Connection.DBConnection;
import app.storemanagement.model.ProductModel;
import app.storemanagement.model.SupplierCbx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
        String sql = "INSERT INTO Product (Product_ID, Product_Name, Category_ID, Unit_Price, Quantity_In_Stock, Supplier_ID, Manufacture_Date, Expiry_Date, Entry_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getCategoryId());
            stmt.setDouble(4, product.getUnitPrice());
            stmt.setInt(5, product.getQuantityInStock());
            stmt.setInt(6, product.getSupplierId());
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
        String sql = "UPDATE Product SET Product_Name = ?, Category_ID = ?, Unit_Price = ?, Quantity_In_Stock = ?, Supplier_ID = ?, Manufacture_Date = ?, Expiry_Date = ?, Entry_Date = ?"
                + "WHERE Product_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(9, product.getId());
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, product.getQuantityInStock());
            stmt.setInt(5, product.getSupplierId());
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
            if (e.getSQLState().equals("23000")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm vì đã có hóa đơn mua sản phẩm này", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public String generateQuery(String sortMethod, String keyword, String searchMethod) {
        String tmp = "";
        if (!keyword.trim().isEmpty()) {
            switch (searchMethod) {
                case "Mã" ->
                    tmp = " WHERE Product_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên" ->
                    tmp = " WHERE Product_Name LIKE N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI ";
                default -> {
                }
            }
        }
        String query = """
                       select Product_ID, Product_Name, Category.Category_Name, Entry_Date 
                        from Product inner join Category on Product.Category_ID = Category.Category_ID
                       inner join Supplier on Product.Supplier_ID = Supplier.Supplier_ID"""
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
    
    public void getCategories(JComboBox<CategoryModel> cateCb) {
        try (Statement St = conn.createStatement(); ResultSet Rs = St.executeQuery("SELECT * FROM Category")) {

            // Tạo một DefaultComboBoxModel để lưu trữ các mục category
            DefaultComboBoxModel<CategoryModel> model = new DefaultComboBoxModel<>();

            while (Rs.next()) {
                int cateId = Rs.getInt("Category_ID");
                String name = Rs.getString("Category_Name");
                // Tạo một đối tượng CategoryItem và thêm nó vào model
                model.addElement(new CategoryModel(cateId, name));
            }

            // Đặt model cho ComboBox
            cateCb.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getSuppliers(JComboBox<SupplierCbx> supplierCb) {
        try (Statement St = conn.createStatement(); ResultSet Rs = St.executeQuery("SELECT Supplier_ID, Supplier_Name FROM Supplier")) {
            // Tạo một DefaultComboBoxModel để lưu trữ các mục supplier
            DefaultComboBoxModel<SupplierCbx> model = new DefaultComboBoxModel<>();

            while (Rs.next()) {
                int supId = Rs.getInt("Supplier_ID");
                String name = Rs.getString("Supplier_Name");

                // Tạo một đối tượng SupplierCbx và thêm nó vào model
                model.addElement(new SupplierCbx(supId, name));
            }

            // Đặt model cho ComboBox
            supplierCb.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
