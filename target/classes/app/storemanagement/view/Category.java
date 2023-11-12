package app.storemanagement.view;

import app.storemanagement.controller.CategoryCtrl;
import app.storemanagement.model.CategoryModel;
import app.storemanagement.model.Connection.DBConnection;
import app.storemanagement.utils.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hung Pham
 */
public class Category extends javax.swing.JPanel {

    private int key = 0;
    private boolean isRowSelected = false;

    /**
     * Creates new form Category
     */
    public Category() {
        initComponents();
        displayCategory((String) categorySort.getSelectedItem()); // Hiển thị dữ liệu với phương thức sắp xếp được chọn
    }

    private void clearTextField() {
        categoryName.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        categoryName = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        categorySort = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(76, 149, 108));
        jLabel15.setText("Phân loại");

        editButton.setBackground(new java.awt.Color(76, 149, 108));
        editButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Sửa");
        editButton.setBorder(null);
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(76, 149, 108));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Thêm");
        addButton.setBorder(null);
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(76, 149, 108));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Xóa");
        deleteButton.setBorder(null);
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(76, 149, 108));
        jLabel20.setText("Tìm kiếm");

        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(76, 149, 108));
        jLabel21.setText("Sắp xếp");

        categorySort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phân loại", "Tên phân loại" }));
        categorySort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categorySortItemStateChanged(evt);
            }
        });

        categoryTable.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phân loại", "Phân loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        categoryTable.setRowHeight(28);
        categoryTable.setSelectionBackground(new java.awt.Color(76, 149, 108));
        categoryTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        categoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(categoryTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(categorySort, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(categoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(jLabel15)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(categorySort, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void displayCategory(String sortMethod) {
        displayCategoryTable(CategoryCtrl.display(sortMethod));
    }

    private void searchCategory(String keyword) {
        displayCategoryTable(CategoryCtrl.search(keyword));
    }

    private void displayCategoryTable(String sql) {
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
            // Đặt tên cột theo thiết kế
            String[] columnNames = {"Mã phân loại", "Tên phân loại"};
            tableModel.setColumnIdentifiers(columnNames);

            categoryTable.setModel(tableModel);
            Rs.close();
            St.close();
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        int id = Util.getNextID("Category_ID", "Category");
        String name = categoryName.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ");
        } else {
            CategoryModel category = new CategoryModel(id, name);
            CategoryCtrl tmp = new CategoryCtrl(DBConnection.getConnection());
            int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm danh mục này?", "Alert",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                boolean success = tmp.add(category);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Đã thêm danh mục");
                }
            }
        }
        isRowSelected = false; //Phòng ngừa trường hợp bấm vào để chỉnh nhưng không chỉnh sửa mà thêm mới
        clearTextField();
        displayCategory((String) categorySort.getSelectedItem()); // Hiển thị lại dữ liệu với phương thức sắp xếp được chọn
    }//GEN-LAST:event_addButtonMouseClicked

    private void categoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) categoryTable.getModel();
        int my_idx = categoryTable.getSelectedRow();
        if (my_idx != -1) {
            // Cập nhật giá trị của key nếu một hàng đã được chọn
            key = Integer.parseInt(model.getValueAt(my_idx, 0).toString());
            isRowSelected = true;
            categoryName.setText(model.getValueAt(my_idx, 1).toString());
        }
    }//GEN-LAST:event_categoryTableMouseClicked

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked
        String name = categoryName.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ");
        } else {
            if (isRowSelected == false) {
                JOptionPane.showMessageDialog(null, "Chọn một danh mục để sửa!");
            } else {
                CategoryModel category = new CategoryModel(key, name);
                CategoryCtrl tmp = new CategoryCtrl(DBConnection.getConnection());
                int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật danh mục này?", "Alert",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    boolean success = tmp.update(category);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Đã cập nhật danh mục");
                    }
                }
            }
        }
        isRowSelected = false; // Đặt lại trạng thái
        clearTextField();
        displayCategory((String) categorySort.getSelectedItem()); // Hiển thị lại dữ liệu với phương thức sắp xếp được chọn
    }//GEN-LAST:event_editButtonMouseClicked

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        if (isRowSelected == false) {
            JOptionPane.showMessageDialog(null, "Chọn một danh mục để xóa!");
        } else {
            CategoryModel category = new CategoryModel(key);
            CategoryCtrl tmp = new CategoryCtrl(DBConnection.getConnection());
            int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa danh mục này?", "Alert",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                boolean success = tmp.delete(category);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Đã xóa danh mục");
                }
            }
        }
        isRowSelected = false; // Đặt lại trạng thái sau khi xóa
        displayCategory((String) categorySort.getSelectedItem()); // Hiển thị lại dữ liệu với phương thức sắp xếp được chọn
        clearTextField();
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void categorySortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categorySortItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selectedMethod = (String) evt.getItem(); // Lấy phương thức sắp xếp được chọn
            displayCategory(selectedMethod); // Gọi hàm displayCategory với phương thức sắp xếp được chọn
        }
    }//GEN-LAST:event_categorySortItemStateChanged

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        Timer timer = new Timer(500, (ActionEvent e) -> {
            String keyword = searchTextField.getText();
            if (keyword.trim().isEmpty()) {
                // Nếu textField rỗng, hiển thị toàn bộ danh sách
                displayCategory((String) categorySort.getSelectedItem());
            } else {
                // Nếu không, thực hiện tìm kiếm dựa trên từ khóa
                searchCategory(keyword);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField categoryName;
    private javax.swing.JComboBox<String> categorySort;
    private javax.swing.JTable categoryTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
