package app.storemanagement.model;

import java.util.Date;

/**
 *
 * @author Hung Pham
 */
public class EmployeeModel extends BaseEntity {

    private String username, password, fullname, gender, role;
    private Date dateOfBirth;
    private int salary;
    
    public EmployeeModel(int id) {
        super(id);
    }

    public EmployeeModel(int id, String username, String password, String fullname, String gender, String role, Date dateOfBirth, int salary) {
        super(id);
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
