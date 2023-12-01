package app.storemanagement.controller;

import app.storemanagement.Dashboard;
import app.storemanagement.model.Connection.DBConnection;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Hung Pham
 */
public class LoginCtrl {
    public boolean checkLogin(String user, String pw, String role, String query) {
        boolean isLoggedIn = false;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, user);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Lấy chuỗi băm từ cơ sở dữ liệu
                    String dbhash = rs.getString("Password");
                    // So sánh mật khẩu với chuỗi băm bằng BCrypt.checkpw
                    // Bắt các ngoại lệ có thể xảy ra
                    try {
                        if (BCrypt.checkpw(pw, dbhash)) {
                            int uid = rs.getInt(1);
                            Dashboard db = new Dashboard(uid, role);
                            db.setInfoLabel(user);
                            db.setVisible(true);
                            isLoggedIn = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Thông tin không chính xác", "Wrong", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IllegalArgumentException e) {
                        // Xử lý ngoại lệ khi mật khẩu hoặc chuỗi băm không hợp lệ
                        System.out.println("Mật khẩu hoặc chuỗi băm không hợp lệ: " + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại sau ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Thông tin không chính xác", "Wrong", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return isLoggedIn;
    }
}
