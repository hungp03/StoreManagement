package app.storemanagement.model;
/**
 *
 * @author AnTran
 */
public class CustomerModel extends BaseEntity {
    private String fullName;
    private String address;
    private String phone;
    
    public CustomerModel(int id) {
        super(id);
    }

    public CustomerModel(int id, String fullName, String address, String phone) {
        super(id);
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
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
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
