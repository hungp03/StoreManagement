package app.storemanagement.controller;

import app.storemanagement.middleware.VerifyAccess;
import app.storemanagement.model.EmployeeModel;
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
public class EmployeeCtrl implements BaseController<EmployeeModel> {

    private Connection conn;

    public EmployeeCtrl() {
    }

    public EmployeeCtrl(Connection conn) {
        this.conn = conn;
    }

    VerifyAccess verifyAccess = new VerifyAccess();

    @Override
    public boolean add(EmployeeModel employee) {
        String sql = "INSERT INTO Employee (Employee_ID, Username, Password, Full_Name, Date_of_Birth, Gender, Role, Salary)"
                + "SELECT ?, ?, ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM Employee WHERE Username = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getUsername());
            stmt.setString(3, verifyAccess.hashPw(employee.getPassword()));
            stmt.setString(4, employee.getFullname());
            stmt.setDate(5, new java.sql.Date(employee.getDateOfBirth().getTime()));
            stmt.setString(6, employee.getGender());
            stmt.setString(7, employee.getRole());
            stmt.setInt(8, employee.getSalary());
            stmt.setString(9, employee.getUsername());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Username đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(EmployeeModel employee) {
        String sql = "UPDATE Employee SET Username=?, Password=?, Full_Name=?, Date_of_Birth=?, Gender=?, Role=?, Salary=?"
                + "WHERE Employee_ID=? AND NOT EXISTS (SELECT 1 FROM Employee WHERE Username = ? AND Employee_ID <> ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(8, employee.getId());
            stmt.setString(1, employee.getUsername());
            stmt.setString(2, verifyAccess.hashPw(employee.getPassword()));
            stmt.setString(3, employee.getFullname());
            stmt.setDate(4, new java.sql.Date(employee.getDateOfBirth().getTime()));
            stmt.setString(5, employee.getGender());
            stmt.setString(6, employee.getRole());
            stmt.setInt(7, employee.getSalary());
            stmt.setString(9, employee.getUsername());
            stmt.setInt(10, employee.getId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(null, "Username đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean delete(EmployeeModel employee) {
        String sql = "DELETE FROM Employee WHERE Employee_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employee.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<EmployeeModel> getEmployees(String keyword, String searchMethod, String sortMethod) {
        List<EmployeeModel> employees = new ArrayList<>();
        String searchQuery = generateSearchQuery(keyword, searchMethod);
        String sql = generateSortQuery(sortMethod, searchQuery);

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Tạo một đối tượng EmployeeModel từ ResultSet
                EmployeeModel employee = new EmployeeModel(
                        rs.getInt("Employee_ID"),
                        rs.getString("Username"),
                        "",
                        rs.getString("Full_Name"),
                        rs.getString("Gender"),
                        rs.getString("Role"),
                        rs.getDate("Date_of_Birth"),
                        rs.getInt("Salary"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private String generateSearchQuery(String keyword, String searchMethod) {
        String tmp = "";
        if (!keyword.trim().isEmpty()) {
            switch (searchMethod) {
                case "Mã NV" ->
                    tmp = " WHERE Employee_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên NV" ->
                    tmp = " WHERE Full_Name LIKE N'%" + keyword.trim() + "%' COLLATE SQL_Latin1_General_CP1253_CI_AI ";
                default -> {
                }
            }
        }
        return "SELECT Employee_ID, Username, Full_Name, Date_of_Birth, Salary, Gender, Role FROM Employee" + tmp;
    }

    private String generateSortQuery(String sortMethod, String searchQuery) {
        return switch (sortMethod) {
            case "Mã NV" ->
                searchQuery + " ORDER BY Employee_ID";
            case "Tên NV" ->
                searchQuery + " ORDER BY Full_Name";
            case "Ngày sinh" ->
                searchQuery + " ORDER BY Date_of_Birth";
            case "Lương" ->
                searchQuery + " ORDER BY Salary";
            default ->
                searchQuery;
        };
    }
}
