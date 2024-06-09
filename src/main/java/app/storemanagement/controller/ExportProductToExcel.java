package app.storemanagement.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tuan
 */
public class ExportProductToExcel {

    private Connection conn;

    public ExportProductToExcel() {
    }

    public ExportProductToExcel(Connection conn) {
        this.conn = conn;
    }

    private boolean exportProductDatatoExcel(String fileName, int condition) {
        try {
            String query = """
                          SELECT Product_ID,Product_Name,
                          Category_Name,Unit_Price,Quantity_In_Stock,
                          Manufacture_Date,Expiry_Date,Entry_Date FROM Product inner join Category
                          on Product.Category_ID = Category.Category_ID
                          """;
            switch (condition) {
                case 0 ->  {
                    query += "WHERE Quantity_In_Stock >0 AND GETDATE() < Expiry_Date";
                }
                case 1 ->  {
                    query += "WHERE GETDATE() > Expiry_Date";
                }
                case 2 ->  {
                }
                default ->  {
                }
            }

            return exportProductToExcel(query, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean exportProductToExcel(String query, String fileName) {

        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("AllProduct");

            // Tạo hàng tiêu đề
            Row headerRow = sheet.createRow(0);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            Map<String, String> columnMapping = new HashMap<>();
            columnMapping.put("Product_ID", "Mã sản phẩm");
            columnMapping.put("Product_Name", "Tên sản phẩm");
            columnMapping.put("Category_Name", "Tên phân loại");
            columnMapping.put("Unit_Price", "Giá trị sản phẩm");
            columnMapping.put("Quantity_In_Stock", "Tổng số sản phẩm");
            columnMapping.put("Description", "Mô tả");
            columnMapping.put("Manufacture_Date", "Ngày sản xuất");
            columnMapping.put("Expiry_Date", "Ngày hết hạn");
            columnMapping.put("Entry_Date", "Ngày nhập");

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
                    cell.setCellValue(resultSet.getString(i));
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
    
    public void exportProductToExcel(int condition) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu vào");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss-mm-HH dd-MM-yyyy");
        String formattedDate = currentDateTime.format(formatter);
        String name = "";
        switch (condition) {
            case 0 ->
                name = "Product_available ";
            case 1 ->
                name = "Expire_Product ";
            case 2 ->
                name = "All_profuct ";
            default -> {
            }
        }
        fileChooser.setSelectedFile(new File(name + formattedDate + ".xlsx"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileChooser.getSelectedFile();
            String fileName = fileToSave.getAbsolutePath();

            if (exportProductDatatoExcel(fileName, condition)) {
                JOptionPane.showMessageDialog(null, "Xuất file thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xuất file thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
