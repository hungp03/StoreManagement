package app.storemanagement.model;

import java.util.Objects;

/**
 *
 * @author Hung Pham
 */
public class SupplierCbx extends BaseEntity {

    private String supplierName;

    public SupplierCbx(int id) {
        super(id);
    }

    public SupplierCbx(int id, String supplierName) {
        super(id);
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
        return supplierName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SupplierCbx that = (SupplierCbx) obj;
        return id == that.id && Objects.equals(supplierName, that.supplierName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.supplierName);
        return hash;
    }
}
