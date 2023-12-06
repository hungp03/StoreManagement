package app.storemanagement.model;

import java.util.Date;

/**
 *
 * @author AnTran
 */
public class InvoiceModel {
    private int id;
    private String customerName;
    private String paymentMethod;
    private Date date;

    public InvoiceModel(int id, String customerName, String paymentMethod, Date date) {
        this.id = id;
        this.customerName = customerName;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}