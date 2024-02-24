package app.storemanagement.controller;

import app.storemanagement.middleware.VerifyAccess;
import app.storemanagement.model.EmployeeModel;
import app.storemanagement.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Hung Pham
 */
public class UserCtrl {

    private Connection conn;

    public UserCtrl() {
    }

    public UserCtrl(Connection conn) {
        this.conn = conn;
    }

    private String getTableBasedOnRole(String role) {
        switch (role) {
            case "admin" -> {
                return "Admin";
            }
            case "banhang", "kho" -> {
                return "Employee";
            }
            default ->
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    private String getIdColumnBasedOnRole(String role) {
        switch (role) {
            case "admin" -> {
                return "Admin_ID";
            }
            case "banhang", "kho" -> {
                return "Employee_ID";
            }
            default ->
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    public boolean updateInfo(UserModel user, String role) {
        String table = getTableBasedOnRole(role);
        String idColumn = getIdColumnBasedOnRole(role);

        String sql = String.format(
                "UPDATE %s SET Username=?, Full_Name=?, Date_of_Birth=? "
                + "WHERE %s =? AND NOT EXISTS (SELECT 1 FROM %s WHERE Username = ? AND %s <> ?)",
                table, idColumn, table, idColumn
        );

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(4, user.getId());
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getFullname());
            stmt.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
            stmt.setString(5, user.getUsername());
            stmt.setInt(6, user.getId());

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

    public boolean checkOldPassword(int uid, String oldPw, String role) {
        boolean success = false;
        String table = getTableBasedOnRole(role);
        String idColumn = getIdColumnBasedOnRole(role);
        String sql = String.format("SELECT Password FROM %s WHERE %s = %d", table, idColumn, uid);

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String pwHash = rs.getString("Password");
                if (BCrypt.checkpw(oldPw, pwHash)) {
                    success = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu, thử lại sau", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }

    public boolean updatePassword(int uid, String newPw, String role) {
        String table = getTableBasedOnRole(role);
        String idColumn = getIdColumnBasedOnRole(role);
        String sql = String.format("UPDATE %s SET Password=? WHERE %s = ?", table, idColumn);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            VerifyAccess verifyAccess = new VerifyAccess();
            stmt.setString(1, verifyAccess.hashPw(newPw));
            stmt.setInt(2, uid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
