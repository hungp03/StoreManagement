
package app.storemanagement.model;

/**
 *
 * @author Hung Pham
 */
public abstract class BaseEntity {
    protected int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
