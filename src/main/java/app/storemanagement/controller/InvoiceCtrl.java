package app.storemanagement.controller;

import app.storemanagement.model.Connection.DBConnection;
import app.storemanagement.utils.Util;
import java.sql.Connection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

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
    public boolean exportInvoiceDataToExcel(String fileName) {
        try {
            String query = "SELECT T1.Invoice_ID, FORMAT(Created_At, 'HH:mm dd/MM/yyyy') as Created_Time, T1.CusName, T1.EmployName, T1.Total_Amount, T1.Payment_Method, " +
                    "T2.Product_Name, T2.Unit_Price, T2.Quantity " +
                    "FROM (SELECT i.Invoice_ID, i.Created_At, c.Full_Name AS CusName, ISNULL(e.Full_Name, ' ') AS EmployName, " +
                    "i.Total_Amount, i.Payment_Method FROM Invoice i " +
                    "JOIN Customer c ON i.Customer_ID = c.Customer_ID " +
                    "LEFT JOIN Employee e ON e.Employee_ID = i.Employee_ID) AS T1 " +
                    "JOIN (SELECT p.Product_Name, p.Unit_Price, c.Quantity, c.Invoice_ID " +
                    "FROM Contain c " +
                    "JOIN Product p ON c.Product_ID = p.Product_ID) AS T2 " +
                    "ON T2.Invoice_ID = T1.Invoice_ID";

            return exportToExcel(query, fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean exportToExcel(String query, String fileName) {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("InvoiceData");

            // Tạo hàng tiêu đề
            Row headerRow = sheet.createRow(0);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            Map<String, String> columnMapping = new HashMap<>();
            columnMapping.put("Invoice_ID", "Mã hóa đơn");
            columnMapping.put("Created_Time", "Thời gian");
            columnMapping.put("CusName", "Tên khách hàng");
            columnMapping.put("EmployName", "Tên nhân viên");
            columnMapping.put("Total_Amount", "Tổng giá trị");
            columnMapping.put("Payment_Method", "Phương thức thanh toán");
            columnMapping.put("Product_Name", "Tên sản phẩm");
            columnMapping.put("Unit_Price", "Đơn giá");
            columnMapping.put("Quantity", "Số lượng");
            
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                String columnName = metaData.getColumnName(i);
                String vietnameseColumnName = columnMapping.getOrDefault(columnName, columnName);
                cell.setCellValue(vietnameseColumnName);
            }

            // Xuất dữ liệu từ ResultSet vào Excel
            int rowNum = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    String columnName = metaData.getColumnName(i);
                    if (i == 5 || i == 8){
                    cell.setCellValue(Util.convertToVND(resultSet.getDouble(i)));
                    }
                    else{
                        cell.setCellValue(resultSet.getString(i));
                    }
                    
                }
            }

            // Ghi vào tệp Excel
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
