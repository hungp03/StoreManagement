package app.storemanagement.utils;

import app.storemanagement.model.Connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Hung Pham
 */
public class Util {

    //Dùng để lấy vai trò của người dùng (admin, nhân viên bán hàng, nhân viên kho)
    public static int eid = 0;
    //Tạo ID tiếp theo
    public static int getNextID(String idName, String tableName) {
        int nextID = 1;
        String query = "SELECT MAX(" + idName + ") FROM " + tableName;
        try (Connection conn = DBConnection.getConnection(); Statement St1 = conn.createStatement(); ResultSet Rs1 = St1.executeQuery(query)) {
            if (Rs1.next()) {
                nextID = Rs1.getInt(1) + 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextID;
    }
    
    
    //Ràng buộc ngày tháng của sản phẩm
    public static boolean checkDate(Date manufactureDate, Date expiryDate, Date entry) {
        Date today = new Date();
        if (manufactureDate.after(entry)) {
            JOptionPane.showMessageDialog(null, "NSX không được sau ngày nhập", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (manufactureDate.after(entry)) {
            JOptionPane.showMessageDialog(null, "NSX không được sau HSD", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (entry.after(expiryDate)) {
            JOptionPane.showMessageDialog(null, "Ngày nhập không được sau HSD", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (manufactureDate.after(today)) {
            JOptionPane.showMessageDialog(null, "NSX không được sau hôm nay", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (entry.after(today)) {
            JOptionPane.showMessageDialog(null, "Ngày nhập không được sau hôm nay", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Lấy thời gian hiện tại
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
    }

    //Kiểm tra đầu vào của Product
    public static boolean validateProductInput(String name, Date manufactureDate, Date expiryDate, Date entry, double unitP, int quantityInStock) {
        if (name.isEmpty() || manufactureDate == null || expiryDate == null || entry == null) {
            JOptionPane.showMessageDialog(null, "Thông tin không đầy đủ", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (unitP < 0 || quantityInStock < 0) {
            JOptionPane.showMessageDialog(null, "Đơn giá và số lượng phải >= 0", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return !(!checkDate(manufactureDate, expiryDate, entry));
    }

    //Kiểm tra email có hợp lệ không
    public static boolean checkEmail(String email) {
        final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        final Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Kiểm tra đầu vào của Customer
    public static boolean validateCustomerInput(String name, String address, String phone, String email) {
        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Thông tin không đầy đủ", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (checkEmail(email) == false) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (isValidPhoneNumber(phone) == false) {
            JOptionPane.showMessageDialog(null, "SĐT không hợp lệ", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Kiểm tra đầu vào của Employee
    public static boolean validateEmployeeInput(String uname, String pword, String fname, Date dob, int slr) {
        if (uname.isEmpty() || pword.isEmpty() || fname.isEmpty() || dob == null || String.valueOf(slr).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Thông tin không đầy đủ", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (isValidUsername(uname) == false) {
            JOptionPane.showMessageDialog(null, "Username không hợp lệ", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (isUnder18(dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
            JOptionPane.showMessageDialog(null, "Nhân viên không được dưới 18 tuổi", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (slr < 0 || slr >= Integer.MAX_VALUE) {
            JOptionPane.showMessageDialog(null, "Lương không hợp lệ", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Kiểm tra đầu vào đã nhập hay chưa (thêm Product)
    public static boolean checkExistInput(String name, Date manufactureDate, Date expiryDate, Date entry, String unitP, String quantityInStock, String description) {
        // Kiểm tra xem có ít nhất một giá trị tồn tại hay không
        return !name.isEmpty() || manufactureDate != null || expiryDate != null || entry != null || !unitP.isEmpty() || !quantityInStock.isEmpty() || !description.isEmpty();
    }

    //Kiểm tra số điện thoại hợp lệ (VD: Khu vực Việt Nam sẽ có dạng +8412345678 hoặc 012345678)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+\\d{1,11}|\\d{9})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    //Ngăn chặn nhân viên bán hàng sử dụng các thao tác họ không được phép (Phân quyền)
    public static boolean authorizationNVBH(String role) {
        if (role.equals("NVBH")) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền sử dụng thao tác này");
            return false;
        }
        return true;
    }

    //Ngăn chặn nhân viên kho sử dụng các thao tác họ không được phép (Phân quyền)
    public static boolean authorizationNVK(String role) {
        if (role.equals("NVK")) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền sử dụng thao tác này");
            return false;
        }
        return true;
    }

    //Kiểm tra nhân viên có đủ 18 tuổi hay không
    public static boolean isUnder18(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return period.getYears() < 18;
    }

    //Kiểm tra username hợp lệ (không có kí tự đặc biệt và dấu cách, chỉ bao gồm chữ và số, không có tiếng việt)
    public static boolean isValidUsername(String username) {
        // Chỉ cho phép các ký tự chữ và số
        String regex = "^[a-zA-Z0-9]+$";
        return Pattern.matches(regex, username);
    }

    //Format tên đúng định dạng. Ví dụ: "NGuyỄn Văn a" -> "Nguyễn Văn A"
    public static String formatName(String name) {
        // Chuyển tất cả các ký tự thành chữ thường
        name = name.toLowerCase();
        // Tách tên thành các phần riêng biệt 
        String[] nameParts = name.split(" ");
        // Khởi tạo một StringBuilder để tạo lại tên theo đúng định dạng
        StringBuilder formattedNameBuilder = new StringBuilder();
        for (String part : nameParts) {
            part = part.trim();
            if (!part.isEmpty()) {
                // Chuyển chữ cái đầu tiên của mỗi phần thành chữ hoa
                String firstLetter = part.substring(0, 1).toUpperCase();
                // Ghép lại phần đã được chuyển đổi vào StringBuilder
                formattedNameBuilder.append(firstLetter).append(part.substring(1)).append(" ");
            }
        }
        return formattedNameBuilder.toString().trim();
    }

    //Mã hóa mật khẩu
    public static String hashPw(String pw) {
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

    //Chuyển đổi giá từ số sang VND
    public static String convertToVND(double number) {
        @SuppressWarnings("deprecation")
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(number);
    }

    public static String vndConvertToNumber(String amount) throws ParseException {
        amount = amount.replace("đ", "").replace(".", "").trim();
        @SuppressWarnings("deprecation")
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return String.valueOf(currencyFormat.parse(amount).intValue());
    }

}
