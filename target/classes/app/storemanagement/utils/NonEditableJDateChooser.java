
package app.storemanagement.utils;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

/**
 *
 * @author Hung Pham
 */
public class NonEditableJDateChooser extends JDateChooser {
    public JTextField createTextField() {
        return new JTextField() {
            @Override
            public boolean isEditable() {
                return true;
            }
        };
    }
}

