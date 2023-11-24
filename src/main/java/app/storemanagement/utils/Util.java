package app.storemanagement.utils;

import app.storemanagement.model.Connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Hung Pham
 */
public class Util {
    public static String tmpID = "";
    public static int getNextID(String idName, String tableName){
        int nextID = 1;
        try {
            Connection conn = DBConnection.getConnection();
            Statement St1 = conn.createStatement();
            ResultSet Rs1 = St1.executeQuery("SELECT MAX(" + idName + ") FROM " + tableName);
            if (Rs1.next()) {
                nextID = Rs1.getInt(1) + 1;
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextID;
    }
    public static boolean checkDate(Date manufactureDate, Date expiryDate,Date entry){
        Date today = new Date();
        if (manufactureDate.after(entry)){
            JOptionPane.showMessageDialog(null, "NSX không được sau ngày nhập");
            return false;
        }
        else if (manufactureDate.after(entry)){
            JOptionPane.showMessageDialog(null, "NSX không được sau HSD");
            return false;
        }
        else if (entry.after(expiryDate)){
            JOptionPane.showMessageDialog(null, "Ngày nhập không được sau HSD");
            return false;
        }
        else if (manufactureDate.after(today)){
            JOptionPane.showMessageDialog(null, "NSX không được sau hôm nay");
            return false;
        }
        else if (entry.after(today)){
            JOptionPane.showMessageDialog(null, "Ngày nhập không được sau hôm nay");
            return false;
        }
        return true;
    }
    
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
    }
    
    public static boolean validateProductInput(String name, Date manufactureDate, Date expiryDate, Date entry, double unitP, int quantityInStock) {
        if (name.isEmpty() || manufactureDate == null || expiryDate == null || entry == null) {
            JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ");
            return false;
        }
        if (unitP < 0 || quantityInStock < 0) {
            JOptionPane.showMessageDialog(null, "Đơn giá và số lượng phải >= 0");
            return false;
        }
        return !(!checkDate(manufactureDate, expiryDate, entry));
    }
}