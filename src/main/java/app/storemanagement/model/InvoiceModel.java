/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.storemanagement.model;

import java.util.Date;

/**
 *
 * @author AnTran
 */
public class InvoiceModel extends BaseEntity{
    private int invoiceId;
    private Date Date;
    private double totalAmount;
    private double customerCash;
    private double returnMoney;
    private String paymentMethod;
    private int employeeId;
    private int customerId;
    
    public InvoiceModel(int id) {
        super(id);
    }

    public InvoiceModel(int invoiceId, Date Date, double totalAmount, double customerCash, double returnMoney, String paymentMethod, int employeeId, int customerId, int id) {
        super(id);
        this.invoiceId = invoiceId;
        this.Date = Date;
        this.totalAmount = totalAmount;
        this.customerCash = customerCash;
        this.returnMoney = returnMoney;
        this.paymentMethod = paymentMethod;
        this.employeeId = employeeId;
        this.customerId = customerId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
}
