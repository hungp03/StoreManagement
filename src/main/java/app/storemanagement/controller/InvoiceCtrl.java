/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.storemanagement.controller;

import app.storemanagement.model.InvoiceModel;
import java.sql.Connection;

/**
 *
 * @author AnTran
 */
public class InvoiceCtrl {

    private Connection conn;

    public InvoiceCtrl() {
    }

    public InvoiceCtrl(Connection conn) {
        this.conn = conn;
    }

    public String getDetailProductTable(String invoiceId) {
        String query = """
                       select p.Product_Name, p.Unit_Price, c.Quantity
                       from Contain c 
                       join Product p on c.Product_ID=p.Product_ID 
                       where c.Invoice_ID=""" + invoiceId;
        return query;
    }

    public String getDetailInvoiceData(String invoiceId) {
        String query = """
                        select i.Invoice_ID, i.Created_At, c.Full_Name as Customer_Name, e.Full_Name as Employee_Name, 
                        i.Total_Amount, i.Payment_Method
                       	from Invoice i
                       	join Customer c on i.Customer_ID=c.Customer_ID
                       	join Employee e on e.Employee_ID=i.Employee_ID
                        where i.Invoice_ID=""" + invoiceId;
        return query;
    }

    public String generateQuery(String sortMethod, String keyword, String searchMethod) {
        String tmp = "";
        if (keyword.trim().isEmpty() == false) {
            switch (searchMethod) {
                case "Mã hóa đơn" ->
                    tmp = " WHERE i.Invoice_ID =" + keyword.trim();
                case "Ngày" ->
                    tmp = " WHERE CONVERT(VARCHAR, i.Date, 23) LIKE N'%" + keyword.trim() + "%'";
                case "Khách hàng" ->
                    tmp = " WHERE c.Full_name LIKE N'%" + keyword.trim() + "%' ";
                default -> {
                }
            }
        }
        String query = "select i.Invoice_ID, c.Full_name, i.Payment_Method, i.Date, i.Total_Amount"
                + " from Invoice as i join Customer as c "
                + "on i.Customer_ID=c.Customer_ID" + tmp;
        switch (sortMethod) {
            case "Mã hóa đơn" ->
                query += " ORDER BY Invoice_ID";
            case "Ngày" ->
                query += " ORDER BY Date";
            default -> {
            }
        }
        return query;
    }
}