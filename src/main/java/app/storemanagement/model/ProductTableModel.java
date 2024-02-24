package app.storemanagement.model;


/**
 *
 * @author Hung Pham
 */
public class ProductTableModel extends BaseEntity {
    private String name;
    private String categoryName;
    private double unitprice;
    private int qty;

    public ProductTableModel(int id) {
        super(id);
    }

    public ProductTableModel(int id, String name, String categoryName,double unitprice, int qty) {
        super(id);
        this.name = name;
        this.categoryName = categoryName;
        this.qty = qty;
        this.unitprice = unitprice;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
