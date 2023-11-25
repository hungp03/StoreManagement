package app.storemanagement.view;

import app.storemanagement.controller.CustomerCtrl;
import app.storemanagement.model.Connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import app.storemanagement.model.CustomerModel;
import app.storemanagement.utils.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Hung Pham
 */
public class Customer extends javax.swing.JPanel {

    private int key = 0;

    /**
     * Creates new form Customer
     */
    public Customer() {
        initComponents();
        displayCustomer((String) customerSortCb.getSelectedItem());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fullName = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        editButton1 = new javax.swing.JButton();
        addButton1 = new javax.swing.JButton();
        deleteButton1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        customerSortCb = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        phone = new javax.swing.JTextField();
        searchCb = new javax.swing.JComboBox<>();
        refresh = new javax.swing.JLabel();

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(76, 149, 108));
        jLabel22.setText("Tên KH");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(76, 149, 108));
        jLabel24.setText("Địa chỉ");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(76, 149, 108));
        jLabel27.setText("Số điện thoại");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(76, 149, 108));
        jLabel28.setText("Email");

        editButton1.setBackground(new java.awt.Color(76, 149, 108));
        editButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        editButton1.setForeground(new java.awt.Color(255, 255, 255));
        editButton1.setText("Sửa");
        editButton1.setBorder(null);
        editButton1.setFocusable(false);
        editButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButton1MouseClicked(evt);
            }
        });

        addButton1.setBackground(new java.awt.Color(76, 149, 108));
        addButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addButton1.setForeground(new java.awt.Color(255, 255, 255));
        addButton1.setText("Thêm");
        addButton1.setBorder(null);
        addButton1.setFocusable(false);
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });

        deleteButton1.setBackground(new java.awt.Color(76, 149, 108));
        deleteButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteButton1.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton1.setText("Xóa");
        deleteButton1.setBorder(null);
        deleteButton1.setFocusable(false);
        deleteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton1ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(76, 149, 108));
        jLabel29.setText("Tìm kiếm");

        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(76, 149, 108));
        jLabel30.setText("Sắp xếp");

        customerSortCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã KH", "Tên KH" }));
        customerSortCb.setFocusable(false);
        customerSortCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                customerSortCbItemStateChanged(evt);
            }
        });

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setRowHeight(28);
        customerTable.setSelectionBackground(new java.awt.Color(76, 149, 108));
        customerTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(customerTable);

        searchCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã KH", "Tên KH", "Số điện thoại" }));
        searchCb.setFocusable(false);
        searchCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchCbItemStateChanged(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(customerSortCb, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(refresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(510, 510, 510)
                                .addComponent(jLabel27))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(phone))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email))))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refresh)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(customerSortCb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29)
                        .addComponent(searchCb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void displayCustomer(String sortMethod) {
        displayCustomerTable(CustomerCtrl.displayQuery(sortMethod, searchTextField.getText(), (String) searchCb.getSelectedItem()));
    }

    private void searchCustomer(String keyword) {
        displayCustomerTable(CustomerCtrl.displayQuery((String) customerSortCb.getSelectedItem(), keyword, (String) searchCb.getSelectedItem()));
    }
    private void editButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButton1MouseClicked
        if (Util.authorizationNVK()) {
            if (customerTable.getSelectedRow() >= 0) {
                try {
                    String customerName = fullName.getText();
                    String customerAddress = address.getText();
                    String customerPhone = phone.getText();
                    String customerEmail = email.getText();

                    if (Util.validateCustomerInput(customerName, customerAddress, customerPhone, customerEmail) && Util.isValidPhoneNumber(customerPhone)) {
                        CustomerModel customer = new CustomerModel(key, customerName, customerAddress, customerPhone, customerEmail);
                        CustomerCtrl tmp = new CustomerCtrl(DBConnection.getConnection());
                        int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật khách hàng này?", "Alert",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            boolean success = tmp.update(customer);
                            if (success) {
                                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                            }
                        }
                        clearTextField();
                        displayCustomer((String) customerSortCb.getSelectedItem());
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Chọn một khách hàng để sửa!");
            }
        }
    }//GEN-LAST:event_editButton1MouseClicked

    private void customerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        int my_idx = customerTable.getSelectedRow();
        if (my_idx >= 0) {
            // Cập nhật giá trị của key nếu một hàng đã được chọn
            key = Integer.parseInt(model.getValueAt(my_idx, 0).toString());
            fullName.setText(model.getValueAt(my_idx, 1).toString());
            address.setText(model.getValueAt(my_idx, 2).toString());
            phone.setText(model.getValueAt(my_idx, 3).toString());
            email.setText(model.getValueAt(my_idx, 4).toString());
        }

    }//GEN-LAST:event_customerTableMouseClicked

    private void customerSortCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_customerSortCbItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selectedMethod = (String) evt.getItem(); // Lấy phương thức sắp xếp được chọn
            displayCustomer(selectedMethod); // Gọi hàm display với phương thức sắp xếp được chọn
        }
    }//GEN-LAST:event_customerSortCbItemStateChanged

    private void addButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton1ActionPerformed
        if (Util.authorizationNVK()) {
            try {
                int id = Util.getNextID("Customer_ID", "Customer");
                String customerName = fullName.getText();
                String customerAddress = address.getText();
                String customerPhone = phone.getText();
                String customerEmail = email.getText();
                if (Util.validateCustomerInput(customerName, customerAddress, customerPhone, customerEmail) && Util.isValidPhoneNumber(customerPhone)) {
                    CustomerModel customer = new CustomerModel(id, customerName, customerAddress, customerPhone, customerEmail);
                    CustomerCtrl tmp = new CustomerCtrl(DBConnection.getConnection());
                    int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm khách hàng này?", "Alert",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        boolean success = tmp.add(customer);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "Đã thêm khách hàng!");
                            displayCustomer((String) customerSortCb.getSelectedItem());
                            clearTextField();
                        }
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addButton1ActionPerformed

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        // TODO add your handling code here:
        Timer timer = new Timer(500, (ActionEvent e) -> {
            String keyword = searchTextField.getText();
            if (keyword.trim().isEmpty()) {
                // Nếu textField rỗng, hiển thị toàn bộ danh sách
                displayCustomer((String) customerSortCb.getSelectedItem());
            } else {
                // Nếu không, thực hiện tìm kiếm dựa trên từ khóa
                searchCustomer(keyword);
            }
        });
        timer.setRepeats(false); // Đảm bảo rằng Timer chỉ thực hiện một lần

        // Thêm DocumentListener vào searchTextField
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                restartTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartTimer();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartTimer();
            }

            public void restartTimer() {
                if (timer.isRunning()) {
                    timer.restart();
                } else {
                    timer.start();
                }
            }
        });
    }//GEN-LAST:event_searchTextFieldKeyTyped

    private void searchCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchCbItemStateChanged

    }//GEN-LAST:event_searchCbItemStateChanged

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        displayCustomerTable("SELECT * FROM Customer");
        searchTextField.setText("");
        searchCb.setSelectedIndex(0);
        customerSortCb.setSelectedIndex(0);
        clearTextField();
    }//GEN-LAST:event_refreshMouseClicked

    private void deleteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton1ActionPerformed
        if (Util.authorizationNVK()) {
            if (customerTable.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Chọn một khách hàng để xóa!");
            } else {
                CustomerModel product = new CustomerModel(key);
                CustomerCtrl tmp = new CustomerCtrl(DBConnection.getConnection());
                int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng này?", "Alert",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    boolean success = tmp.delete(product);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                    }
                }
            }
            clearTextField();
            displayCustomer((String) customerSortCb.getSelectedItem());
        }
    }//GEN-LAST:event_deleteButton1ActionPerformed

    private void displayCustomerTable(String sql) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement St = conn.createStatement();
            ResultSet Rs = St.executeQuery(sql);
            DefaultTableModel tableModel = new DefaultTableModel();
            int columnCount = Rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(Rs.getMetaData().getColumnName(i));
            }

            // Đổ dữ liệu từ ResultSet vào DefaultTableModel
            while (Rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = Rs.getObject(i);
                }
                tableModel.addRow(row);
            }
            String[] columnNames = {"Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại", "Email"};
            tableModel.setColumnIdentifiers(columnNames);

            customerTable.setModel(tableModel);
            Rs.close();
            St.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void clearTextField() {
        fullName.setText("");
        address.setText("");
        phone.setText("");
        email.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton1;
    private javax.swing.JTextField address;
    private javax.swing.JComboBox<String> customerSortCb;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteButton1;
    private javax.swing.JButton editButton1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fullName;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField phone;
    private javax.swing.JLabel refresh;
    private javax.swing.JComboBox<String> searchCb;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
