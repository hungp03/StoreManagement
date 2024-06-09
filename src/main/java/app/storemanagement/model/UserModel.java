package app.storemanagement.model;

import java.util.Date;

/**
 *
 * @author Hung Pham
 */
public class UserModel extends BaseEntity {

    private String username, fullname;
    private Date dateOfBirth;

    public UserModel(int id) {
        super(id);
    }

    public UserModel(int id, String username, String fullname, Date dateOfBirth) {
        super(id);
        this.username = username;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
