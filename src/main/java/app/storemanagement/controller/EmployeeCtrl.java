package app.storemanagement.controller;

import app.storemanagement.model.EmployeeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author locle
 */
public class EmployeeCtrl implements BaseController<EmployeeModel> {

    private Connection conn;

    public EmployeeCtrl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(EmployeeModel employee) {
        String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getPosition());
            stmt.setDouble(4, employee.getSalary());
            stmt.setString(5, employee.getBirthDate());
            stmt.setString(6, employee.getEmail());
            stmt.setString(7, employee.getPhoneNumber());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(EmployeeModel employee) {
        String sql = "UPDATE Employee SET Employee_Name = ?, Position = ?, Salary = ?, Birth_Date = ? WHERE Employee_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getPosition());
            stmt.setDouble(3, employee.getSalary());
            stmt.setString(4, employee.getBirthDate());
            stmt.setInt(5, employee.getId());
            stmt.setString(6, employee.getPhoneNumber());
            stmt.setInt(7, employee.getId());
            stmt.executeUpdate();
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
            if (e.getSQLState().equals("23000")) {
                JOptionPane.showMessageDialog(null, "Cannot delete employee as there are records associated with this employee.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public static String displayQuery(String sortMethod) {
        String query = "SELECT * FROM Employee";
        switch (sortMethod) {
            case "Mã nhân viên" -> query += " ORDER BY Employee_ID";
            case "Tên nhân viên" -> query += " ORDER BY Employee_Name";
            case "Lương" -> query += " ORDER BY Salary";
            case "Ngày sinh" -> query += " ORDER BY Birth_Date";
            case "Email" -> query += " ORDER BY Email";
            case "Số điện thoại" -> query += " ORDER BY Phone";
            default -> {
            }
        }
        return query;
    }

    public static String searchQuery(String keyword, String sortMethod) {
        String tmp = "";
        switch (sortMethod) {
            case "Mã nhân viên" -> tmp += "Employee_ID";
            case "Tên nhân viên" -> tmp += "Employee_Name";
            case "Lương" -> tmp += "Salary";
            case "Ngày sinh" -> tmp += "Birth_Date";
            case "Email" -> tmp += " ORDER BY Email";
            case "Số điện thoại" -> tmp += " ORDER BY Phone";
            default -> {
            }
        }
        String query = "SELECT * FROM Employee WHERE Employee_Name LIKE '%" + keyword + "%' OR Position LIKE '%" + keyword + "%' COLLATE Vietnamese_CI_AI ORDER BY " + tmp;
        return query;
    }
}