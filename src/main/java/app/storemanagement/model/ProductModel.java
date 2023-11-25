package app.storemanagement.model;
import java.util.Date;
/**
 *
 * @author Hung Pham
 */

public class ProductModel extends BaseEntity{
    private String name;
    private int categoryId;
    private double unitPrice;
    private int quantityInStock;
    private String description;
    private Date manufactureDate;
    private Date expiryDate;
    private Date entryDate;

    public ProductModel(int id) {
        super(id);
    }

    public ProductModel(int id, String name, int categoryId, double unitPrice, int quantityInStock, String description, Date manufactureDate, Date expiryDate, Date entryDate) {
        super(id);
        this.name = name;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.quantityInStock = quantityInStock;
        this.description = description;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.entryDate = entryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
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