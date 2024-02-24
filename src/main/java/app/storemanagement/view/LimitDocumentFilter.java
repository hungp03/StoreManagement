package app.storemanagement.view;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LimitDocumentFilter extends DocumentFilter {
    private int limit;

    public LimitDocumentFilter(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (this.limit > 0 && fb.getDocument().getLength() + string.length() - limit > 0) {
            string = string.substring(0, limit - fb.getDocument().getLength());
        }
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (this.limit > 0 && fb.getDocument().getLength() + text.length() - length - limit > 0) {
            text = text.substring(0, limit - fb.getDocument().getLength() + length);
        }
        super.replace(fb, offset, length, text, attrs);
    }
}

