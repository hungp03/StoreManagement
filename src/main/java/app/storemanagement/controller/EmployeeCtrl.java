package app.storemanagement.controller;

import app.storemanagement.model.EmployeeModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class EmployeeCtrl implements BaseController<EmployeeModel> {

    private Connection conn;

    public EmployeeCtrl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(EmployeeModel employee) {
        String sql = "INSERT INTO Employee (Employee_ID, Username, Password, Full_Name, Date_of_Birth, Gender, Role, Salary)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getUsername());
            stmt.setString(3, employee.getPassword());
            stmt.setString(4, employee.getFullname());
            stmt.setDate(5, new java.sql.Date(employee.getDateOfBirth().getTime()));
            stmt.setString(6, employee.getGender());
            stmt.setString(7, employee.getRole());
            stmt.setInt(8, employee.getSalary());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(EmployeeModel employee) {
        String sql = "UPDATE Employee SET Username=?, Password=?, Full_Name=?, Date_of_Birth=?, Gender=?, Role=?, Salary=?"
                + "WHERE Employee_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(8, employee.getId());
            stmt.setString(1, employee.getUsername());
            stmt.setString(2, employee.getPassword());
            stmt.setString(3, employee.getFullname());
            stmt.setDate(4, new java.sql.Date(employee.getDateOfBirth().getTime()));
            stmt.setString(5, employee.getGender());
            stmt.setString(6, employee.getRole());
            stmt.setInt(7, employee.getSalary());
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
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static String displayQuery(String sortMethod, String keyword, String searchMethod) {
        String tmp = "";
        if (keyword.trim().isEmpty() == false) {
            switch (searchMethod) {
                case "Mã NV" ->
                    tmp = " WHERE Employee_ID LIKE N'%" + keyword.trim() + "%' ";
                case "Tên NV" ->
                    tmp = " WHERE Full_Name LIKE N'%" + keyword + "%' COLLATE Vietnamese_CI_AI ";
                default -> {
                }
            }
        }
        String query = "select Employee_ID, Username, Full_Name, Date_of_Birth, Salary, Gender, Role from Employee" + tmp;
        switch (sortMethod) {
            case "Mã NV" ->
                query += " ORDER BY Employee_ID";
            case "Tên NV" ->
                query += " ORDER BY Full_Name";
            case "Ngày sinh" ->
                query += " ORDER BY Date_of_Birth";
            case "Lương" ->
                query += " ORDER BY Salary";
            default -> {
            }
        }
        return query;
    }
}
