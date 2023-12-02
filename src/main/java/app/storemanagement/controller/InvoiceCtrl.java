package app.storemanagement.controller;

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

    public String getDetailProductTable(int invoiceId) {
        String query = """
                       select p.Product_Name, p.Unit_Price, c.Quantity
                       from Contain c 
                       join Product p on c.Product_ID=p.Product_ID 
                       where c.Invoice_ID = """ + invoiceId;
        return query;
    }

    public String getDetailInvoiceData(int invoiceId) {
        String query = """
                        SELECT FORMAT(Created_At, 'HH:mm dd/MM/yyyy') as Created_Time, c.Full_Name AS Customer_Name, 
                               ISNULL(e.Full_Name, ' ') AS Employee_Name, 
                               i.Total_Amount, i.Payment_Method
                        FROM Invoice i
                        JOIN Customer c ON i.Customer_ID = c.Customer_ID
                        LEFT JOIN Employee e ON e.Employee_ID = i.Employee_ID
                        WHERE i.Invoice_ID = """ + invoiceId;
        return query;
    }

    public String generateQuery(String sortMethod, String keyword, String searchMethod) {
        String tmp = "";
        if (keyword.trim().isEmpty() == false) {
            switch (searchMethod) {
                case "Mã hóa đơn" ->
                    tmp = " WHERE i.Invoice_ID LIKE N'%" + keyword.trim() + "%'";
                case "Ngày" ->
                    tmp = " WHERE CONVERT(VARCHAR, i.Date, 23) LIKE N'%" + keyword.trim() + "%'";
                case "Khách hàng" ->
                    tmp = " WHERE c.Full_name LIKE N'%" + keyword.trim() + "%' COLLATE Vietnamese_CI_AI";
                default -> {
                }
            }
        }
        String query = "select i.Invoice_ID, c.Full_name, i.Payment_Method, i.Date"
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
