package app.storemanagement.view;

import app.storemanagement.model.InvoiceData;
import app.storemanagement.controller.PrintInvoiceCtrl;

/**
 *
 * @author Hung Pham
 */
public class PrintInvoice_v2 {

    private final int invoiceID;
    private boolean success = false;

    public boolean isSuccess() {
        return success;
    }
    
    public PrintInvoice_v2(int invoiceID) {
        this.invoiceID = invoiceID;
        PrintInvoiceCtrl pic = new PrintInvoiceCtrl();
        InvoiceData invoiceData = pic.retrieveInvoiceData(this.invoiceID);
        success = pic.printInvoice(invoiceData);
    }
}
