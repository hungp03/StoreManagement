package app.storemanagement.controller;

import app.storemanagement.view.PrintInvoice_v2;
import app.storemanagement.model.Connection.DBConnection;
import app.storemanagement.model.InvoiceData;
import app.storemanagement.utils.Util;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hung Pham
 */
public class PrintInvoiceCtrl {

    private String fontPath = "font\\Tahoma Regular font.ttf";

    public InvoiceData retrieveInvoiceData(int id) {
        InvoiceData invoiceData = new InvoiceData();
        String sql = "SELECT e.Full_Name AS Employee_Name, c.Full_Name AS Customer_Name, "
                + "i.Total_Amount, i.Customer_Cash, i.Return_Money, "
                + "FORMAT(Created_At, 'HH:mm dd/MM/yyyy') as Created_Time "
                + "FROM Invoice i JOIN Employee e ON i.Employee_ID = e.Employee_ID "
                + "JOIN Customer c ON i.Customer_ID = c.Customer_ID "
                + "WHERE Invoice_ID = " + id;

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                invoiceData.setEmployeeName(rs.getString("Employee_Name"));
                invoiceData.setCustomerName(rs.getString("Customer_Name"));
                invoiceData.setTotalAmount(rs.getDouble("Total_Amount"));
                invoiceData.setCustomerCash(rs.getDouble("Customer_Cash"));
                invoiceData.setReturnMoney(rs.getDouble("Return_Money"));
                invoiceData.setCreatedTime(rs.getString("Created_Time"));
                invoiceData.setInvoiceID(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoiceData;
    }

    public boolean createInvoicePDF(InvoiceData invoiceData) {
        boolean printedSuccessfully = false;

        try {
            PdfWriter writer = new PdfWriter("D:\\invoice.pdf");
            PdfDocument pdf = new PdfDocument(writer);

            try (Document document = new Document(pdf)) {
                addStoreInfo(document);

                addInvoiceDetails(document, invoiceData);

                addProductList(document, invoiceData);

                addSummary(document, invoiceData);

                addThanksSection(document);
                document.close();
                printedSuccessfully = true; // Gán giá trị true nếu in thành công
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintInvoice_v2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return printedSuccessfully; // Trả về giá trị true nếu in thành công, ngược lại là false
    }

    public boolean printInvoice(InvoiceData invoiceData) {
        return createInvoicePDF(invoiceData);
    }

    public void addStoreInfo(Document document) {
        try {
            PdfFont font = PdfFontFactory.createFont(fontPath, "Identity-H", true);

            // Thêm thông tin cửa hàng vào hóa đơn
            String storeName = "Cửa hàng ABC";
            String address = "1 Đường X, Phường Y, TP. Thủ Đức";
            String phoneNumber = "SĐT:  0123456789";

            Paragraph storeInfo = new Paragraph()
                    .add(new Text(storeName + "\n").setBold())
                    .add(new Text(address + "\n"))
                    .add(new Text(phoneNumber + "\n"))
                    .add(new Text("----------------------------------------------------------------------------------------------------------------\n"));
            document.add(storeInfo.setTextAlignment(TextAlignment.CENTER).setFont(font));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addInvoiceDetails(Document document, InvoiceData invoiceData) {
        try {
            PdfFont font = PdfFontFactory.createFont(fontPath, "Identity-H", true);

            // Thêm thông tin hóa đơn và khách hàng
            Paragraph title = new Paragraph("Hóa đơn").setFontSize(20);
            title.setFont(font).setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            Paragraph invoiceIdAndTime = new Paragraph();
            invoiceIdAndTime.setFont(font).addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            invoiceIdAndTime.add("Mã hóa đơn: " + invoiceData.getInvoiceID()).add(new Tab()).add("Thời gian: " + invoiceData.getCreatedTime());
            document.add(invoiceIdAndTime);

            Paragraph customerNameParagraph = new Paragraph("Khách hàng: " + invoiceData.getCustomerName()).setFont(font);
            document.add(customerNameParagraph);

            Paragraph employeeNameParagraph = new Paragraph("Nhân viên bán hàng: " + invoiceData.getEmployeeName()).setFont(font);
            document.add(employeeNameParagraph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProductList(Document document, InvoiceData invoiceData) {
        try {
            PdfFont font = PdfFontFactory.createFont(fontPath, "Identity-H", true);

            Table table = new Table(new float[]{3, 1, 1}); // Số cột đã thay đổi và tỷ lệ chiều rộng
            table.setWidthPercent(100);

            String productSql = "SELECT p.Product_Name, p.Unit_Price, c.Quantity "
                    + "FROM Invoice i INNER JOIN Contain c ON i.Invoice_ID = c.Invoice_ID "
                    + "INNER JOIN Product p ON p.Product_ID = c.Product_ID WHERE i.Invoice_ID = " + invoiceData.getInvoiceID();

            try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(productSql)) {
                // Thêm tiêu đề cột
                table.addCell(new Cell().add(new Paragraph("Tên sản phẩm").setFont(font)));
                table.addCell(new Cell().add(new Paragraph("Số lượng").setFont(font).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph("Giá").setFont(font).setTextAlignment(TextAlignment.CENTER)));

                while (rs.next()) {
                    String productName = rs.getString("Product_Name");
                    Double unitPrice = rs.getDouble("Unit_Price");
                    String quantity = rs.getString("Quantity");

                    table.addCell(new Cell().add(new Paragraph(productName).setFont(font)));
                    table.addCell(new Cell().add(new Paragraph(quantity).setFont(font).setTextAlignment(TextAlignment.CENTER)));
                    table.addCell(new Cell().add(new Paragraph(Util.convertToVND(unitPrice)).setFont(font).setTextAlignment(TextAlignment.CENTER)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            document.add(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSummary(Document document, InvoiceData invoiceData) {
        try {
            PdfFont font = PdfFontFactory.createFont(fontPath, "Identity-H", true);

            Paragraph total = new Paragraph();
            total.setFont(font).addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            total.add("Tổng tiền: ").add(new Tab()).add(Util.convertToVND(invoiceData.getTotalAmount()));
            document.add(total);

            Paragraph moneyGiven = new Paragraph();
            moneyGiven.setFont(font).addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            moneyGiven.add("Tiền khách đưa: ").add(new Tab()).add(Util.convertToVND(invoiceData.getCustomerCash()));
            document.add(moneyGiven);

            Paragraph change = new Paragraph();
            change.setFont(font).addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            change.add("Tiền trả lại: ").add(new Tab()).add(Util.convertToVND(invoiceData.getReturnMoney()));
            document.add(change);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addThanksSection(Document document) {
        try {
            PdfFont font = PdfFontFactory.createFont(fontPath, "Identity-H", true);

            Paragraph thanks = new Paragraph()
                    .add(new Text("----------------------------------------------------------------------------------------------------------------\n"))
                    .add(new Text("Thank you!"));
            thanks.setFont(font).setTextAlignment(TextAlignment.CENTER);
            document.add(thanks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
