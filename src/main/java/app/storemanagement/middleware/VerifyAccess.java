package app.storemanagement.middleware;

import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Hung Pham
 */
public class VerifyAccess {
    //Ngăn chặn nhân viên bán hàng sử dụng các thao tác họ không được phép (Phân quyền)
    public boolean authorizationNVBH(String role) {
        if (role.equals("banhang")) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền sử dụng thao tác này");
            return false;
        }
        return true;
    }

    //Ngăn chặn nhân viên kho sử dụng các thao tác họ không được phép (Phân quyền)
    public boolean authorizationNVK(String role) {
        if (role.equals("kho")) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền sử dụng thao tác này");
            return false;
        }
        return true;
    }
    
     //Mã hóa mật khẩu
    public String hashPw(String pw) {
        String salt = BCrypt.gensalt(10);
        String hashed = null;
        try {
            hashed = BCrypt.hashpw(pw, salt);
        } catch (IllegalArgumentException e) {
            // Xử lý ngoại lệ khi mật khẩu hoặc muối là null hoặc không hợp lệ
            System.out.println("Mật khẩu hoặc muối không hợp lệ: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình mã hóa mật khẩu", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return hashed;
    }
}
