package app.storemanagement.model;

import java.util.Objects;
/**
 *
 * @author ADMIN
 */
public class EmployeeModel extends BaseEntity {

    private String name;
    private String position;
    private double salary;
    private String birthDate;
    private String email;
    private String phoneNumber;

    public EmployeeModel(int id) {
        super(id);
    }

    public EmployeeModel(int id, String name, String position, double salary, String birthDate, String email, String phoneNumber) {
        super(id);
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Kiểm tra điều kiện email
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        // Kiểm tra điều kiện số điện thoại
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    // ComboBox sẽ sử dụng phương thức này để hiển thị tên của mỗi mục
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EmployeeModel that = (EmployeeModel) obj;
        return id == that.id && Objects.equals(name, that.name)
                && Objects.equals(position, that.position)
                && salary == that.salary
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(email, that.email)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    // Kiểm tra định dạng email hợp lệ
    private boolean isValidEmail(String email) {
        return email != null && email.matches(".+@.+");
    }

    // Kiểm tra định dạng số điện thoại hợp lệ (chỉ chấp nhận chữ số)
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d+");
    }
}
