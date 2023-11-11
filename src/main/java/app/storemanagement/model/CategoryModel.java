
package app.storemanagement.model;

/**
 *
 * @author Hung Pham
 */
public class CategoryModel extends BaseEntity{
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
    
}
