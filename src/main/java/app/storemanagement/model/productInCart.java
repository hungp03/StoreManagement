
package app.storemanagement.model;

public class productInCart extends BaseEntity{
    private String pName;
    private int qty;
    private double unitPrice;

    public productInCart(int id) {
        super(id);
    }
       
    public productInCart(int id, String pName,double unitPrice, int qty) {
        super(id);
        this.pName = pName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
