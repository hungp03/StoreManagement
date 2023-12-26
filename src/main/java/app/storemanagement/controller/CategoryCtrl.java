package app.storemanagement.controller;

import app.storemanagement.model.CategoryModel;
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
public class CategoryCtrl implements BaseController<CategoryModel> {

    private Connection conn;

    public CategoryCtrl() {

    }

    public CategoryCtrl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(CategoryModel category) {
        String sql = "INSERT INTO Category (Category_ID, Category_Name)"
                + "SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM Category WHERE Category_Name = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category.getId());
            stmt.setString(2, category.getCategoryName());
            stmt.setString(3, category.getCategoryName());
            int affectedRows = stmt.executeUpdate();
            //Bắt lỗi nếu tìm trong cơ sở dữ liệu đã thấy tồn tại phân loại => trả về lỗi và không có hàng nào được thêm
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Phân loại đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(CategoryModel category) {
        String sql = "UPDATE Category SET Category_Name=?"
                + "WHERE Category_ID=? AND NOT EXISTS (SELECT 1 FROM Category WHERE Category_Name = ? AND Category_ID <> ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(2, category.getId());
            stmt.setString(1, category.getCategoryName());
            stmt.setString(3, category.getCategoryName());
            stmt.setInt(4, category.getId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Phân loại đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
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
            if (e.getSQLState().equals("23000")) {
                JOptionPane.showMessageDialog(null, "Không thể xóa phân loại do đã có sản phẩm liên kết đến phân loại này", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public List<CategoryModel> getCategories(String keyword, String sortMethod) {
        List<CategoryModel> categories = new ArrayList<>();
        String searchQuery = generateSearchQuery(keyword);
        String sql = generateSortQuery(sortMethod, searchQuery);

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Tạo một đối tượng CategoryModel từ ResultSet
                CategoryModel category = new CategoryModel(rs.getInt("Category_ID"), rs.getString("Category_Name"));
                category.setProductQty(rs.getInt("Sosanpham"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    private String generateSearchQuery(String keyword) {
        String tmp = "";
        if (!keyword.trim().isEmpty()) {
            tmp = " WHERE Category_Name LIKE N'%" + keyword.trim() + "%' COLLATE SQL_Latin1_General_CP1253_CI_AI ";
        }
        return """
               select Category.*, count(Product_ID) as Sosanpham from
               Category left join Product on Product.Category_ID = Category.Category_ID
               group by Category.Category_ID, Category_Name""" + tmp;
    }

    private String generateSortQuery(String sortMethod, String searchQuery) {
        return switch (sortMethod) {
            case "Mã phân loại" ->
                searchQuery + " ORDER BY Category_ID";
            case "Tên phân loại" ->
                searchQuery + " ORDER BY Category_Name";
            default ->
                searchQuery;
        };
    }

}
