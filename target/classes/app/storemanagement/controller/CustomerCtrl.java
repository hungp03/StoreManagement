package app.storemanagement.controller;
/**
 *
 * @author AnTran
 */
import app.storemanagement.model.CustomerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CustomerCtrl implements BaseController<CustomerModel>{
    private Connection conn;

    public CustomerCtrl(Connection conn) {
        this.conn = conn;
    }
    

    @Override
    public boolean add(CustomerModel customer) {
        String sql = "INSERT INTO Customer (Customer_ID, Full_Name, Address, Phone, Email)"
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean update(CustomerModel customer) {
        String sql = "UPDATE Customer SET Full_Name=?, Address=?, Phone=?, Email=?"
                 + "WHERE Customer_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(5, customer.getId());
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean delete(CustomerModel customer) {
        String sql = "DELETE FROM Customer WHERE Customer_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customer.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
