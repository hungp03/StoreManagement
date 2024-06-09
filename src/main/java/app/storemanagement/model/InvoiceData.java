package app.storemanagement.model;

/**
 *
 * @author Hung Pham
 */
public class InvoiceData {

    private int invoiceID;
    private String employeeName;
    private String customerName;
    private double totalAmount;
    private double customerCash;
    private double returnMoney;
    private String createdTime;

    public InvoiceData() {
    }

    public InvoiceData(int invoiceID, String employeeName, String customerName, double totalAmount, double customerCash, double returnMoney, String createdTime) {
        this.invoiceID = invoiceID;
        this.employeeName = employeeName;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.customerCash = customerCash;
        this.returnMoney = returnMoney;
        this.createdTime = createdTime;
    }
    
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCustomerCash() {
        return customerCash;
    }

    public void setCustomerCash(double customerCash) {
        this.customerCash = customerCash;
    }

    public double getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(double returnMoney) {
        this.returnMoney = returnMoney;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
