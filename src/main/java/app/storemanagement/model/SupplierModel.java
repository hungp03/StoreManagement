package app.storemanagement.model;
/**
 *
 * @author Hung Pham
 */
public class SupplierModel extends BaseEntity {
    private String fullName;
    private String address;
    private String phone;
    private String email;
    
    public SupplierModel(int id) {
        super(id);
    }

    public SupplierModel(int id, String fullName, String address, String phone, String email) {
        super(id);
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

