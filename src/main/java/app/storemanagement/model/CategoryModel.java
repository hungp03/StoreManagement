package app.storemanagement.model;

import java.util.Objects;

/**
 *
 * @author Hung Pham
 */
public class CategoryModel extends BaseEntity {

    private String categoryName;

    public CategoryModel(int id) {
        super(id);
    }

    public CategoryModel(int id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        return categoryName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CategoryModel that = (CategoryModel) obj;
        return id == that.id && Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.categoryName);
        return hash;
    }
}
