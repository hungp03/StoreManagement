package app.storemanagement.view;

import app.storemanagement.controller.ProductCtrl;
import app.storemanagement.controller.ExportProductToExcel;
import app.storemanagement.middleware.VerifyAccess;
import app.storemanagement.model.Connection.DBConnection;
import app.storemanagement.model.ProductModel;
import app.storemanagement.model.ProductTableModel;
import app.storemanagement.utils.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hung Pham
 */
public class Product extends javax.swing.JPanel {

    private int key = 0;
    private String userRole;

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    private final VerifyAccess verifyAccess = new VerifyAccess();
    private AddProduct ap = null;
    private ProductDetail pd = null;

    /**
     * Creates new form Product
     */
    public Product() {
        initComponents();
        showMetrics();
        displayProduct();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        totalInvoice = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        todayRevenue = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        totalRevenue = new javax.swing.JLabel();
        addProduct = new javax.swing.JButton();
        deleteProduct = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        productSort = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        detailButton = new javax.swing.JButton();
        refresh = new javax.swing.JLabel();
        searchCb = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        totalProduct = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        totalSold = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        warehouse = new javax.swing.JLabel();
        exportProductToExcel = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(6, 214, 160));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/order.png"))); // NOI18N
        jLabel1.setText("Tổng số đơn hàng");

        totalInvoice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalInvoice.setForeground(new java.awt.Color(255, 255, 255));
        totalInvoice.setText("0 đơn hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(totalInvoice)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalInvoice)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(251, 86, 7));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cash.png"))); // NOI18N
        jLabel7.setText("Doanh thu hôm nay");

        todayRevenue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        todayRevenue.setForeground(new java.awt.Color(255, 255, 255));
        todayRevenue.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(todayRevenue)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todayRevenue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(33, 158, 188));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cash.png"))); // NOI18N
        jLabel9.setText("Tổng doanh thu");

        totalRevenue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalRevenue.setForeground(new java.awt.Color(255, 255, 255));
        totalRevenue.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(totalRevenue)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalRevenue)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        addProduct.setBackground(new java.awt.Color(76, 149, 108));
        addProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addProduct.setForeground(new java.awt.Color(255, 255, 255));
        addProduct.setText("Thêm");
        addProduct.setBorder(null);
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        deleteProduct.setBackground(new java.awt.Color(76, 149, 108));
        deleteProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteProduct.setForeground(new java.awt.Color(255, 255, 255));
        deleteProduct.setText("Xóa");
        deleteProduct.setBorder(null);
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(76, 149, 108));
        jLabel47.setText("Tìm kiếm");

        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(76, 149, 108));
        jLabel48.setText("Sắp xếp");

        productSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã SP", "Tên SP", "Ngày nhập hàng" }));
        productSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productSortItemStateChanged(evt);
            }
        });

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Phân loại", "Ngày nhập hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.setRowHeight(28);
        productTable.setSelectionBackground(new java.awt.Color(76, 149, 108));
        productTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        productTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(productTable);

        detailButton.setBackground(new java.awt.Color(76, 149, 108));
        detailButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        detailButton.setForeground(new java.awt.Color(255, 255, 255));
        detailButton.setText("Xem chi tiết");
        detailButton.setBorder(null);
        detailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailButtonActionPerformed(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });

        searchCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên" }));
        searchCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchCbItemStateChanged(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(6, 214, 160));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/products.png"))); // NOI18N
        jLabel3.setText("Tổng số sản phẩm");

        totalProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalProduct.setForeground(new java.awt.Color(255, 255, 255));
        totalProduct.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(totalProduct)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalProduct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(251, 86, 7));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/paid.png"))); // NOI18N
        jLabel10.setText("Số lượng đã bán");

        totalSold.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalSold.setForeground(new java.awt.Color(255, 255, 255));
        totalSold.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(totalSold)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalSold)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(33, 158, 188));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/warehouse.png"))); // NOI18N
        jLabel11.setText("Số lượng trong kho");

        warehouse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        warehouse.setForeground(new java.awt.Color(255, 255, 255));
        warehouse.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(warehouse)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warehouse)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        exportProductToExcel.setBackground(new java.awt.Color(76, 149, 108));
        exportProductToExcel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exportProductToExcel.setForeground(new java.awt.Color(255, 255, 255));
        exportProductToExcel.setText("Xuất file excel");
        exportProductToExcel.setBorder(null);
        exportProductToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportProductToExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productSort, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(deleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(detailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(exportProductToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(refresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18)
                                .addComponent(searchCb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(exportProductToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(detailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refresh)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47)
                        .addComponent(productSort, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel48)
                        .addComponent(searchCb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void displayProduct() {
        displayProductTable((String) productSort.getSelectedItem(), searchTextField.getText(), (String) searchCb.getSelectedItem());
    }

    private void showMetrics() {
        String query = """
                       SELECT COUNT(Product_ID) AS totalProduct, SUM(Quantity_In_Stock) AS totalQuantity,
                       (SELECT SUM(Quantity) FROM Contain) AS totalSold FROM Product
                       """;
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet Rs = st.executeQuery(query)) {
            if (Rs.next()) {
                int countProduct = Rs.getInt("totalProduct");
                int countQty = Rs.getInt("totalQuantity");
                int countSold = Rs.getInt("totalSold");
                totalProduct.setText(String.valueOf(countProduct));
                totalSold.setText(String.valueOf(countSold));
                warehouse.setText(String.valueOf(countQty));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void displayProductTable(String sortMethod, String keyword, String searchMethod) {
        ProductCtrl product = new ProductCtrl(DBConnection.getConnection());
        List<ProductTableModel> products = product.getProducts(keyword, searchMethod, sortMethod);

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] columnNames = {"Mã SP", "Tên SP", "Tên phân loại", "Đơn giá", "Số lượng"};
        tableModel.setColumnIdentifiers(columnNames);

        for (ProductTableModel p : products) {
            Object[] row = new Object[5];
            row[0] = p.getId();
            row[1] = p.getName();
            row[2] = p.getCategoryName();
            row[3] = Util.convertToVND(p.getUnitprice());
            row[4] = p.getQty();
            tableModel.addRow(row);
        }

        productTable.setModel(tableModel);
    }

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        int my_idx = productTable.getSelectedRow();
        if (my_idx > -1) {
            // Lấy ID từ hàng được chọn
            key = Integer.parseInt(model.getValueAt(my_idx, 0).toString());
        }
    }//GEN-LAST:event_productTableMouseClicked

    private void productSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productSortItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selectedMethod = (String) evt.getItem(); // Lấy phương thức sắp xếp được chọn
            // Gọi hàm display với phương thức sắp xếp được chọn
            displayProductTable(selectedMethod, searchTextField.getText(), (String) searchCb.getSelectedItem());
        }
    }//GEN-LAST:event_productSortItemStateChanged

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        Timer timer = new Timer(500, (ActionEvent e) -> {
            displayProduct();
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

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        searchTextField.setText("");
        productSort.setSelectedIndex(0);
        searchCb.setSelectedIndex(0);
        showMetrics();
        displayProduct();
    }//GEN-LAST:event_refreshMouseClicked

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        if (verifyAccess.authorizationNVBH(userRole)) {
            if (ap == null) {
                ap = new AddProduct();
                ap.setVisible(true);
                ap.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        if (ap.isDataAdded()) {
                            // Gọi phương thức cập nhật từ JFrame gốc khi JFrame mới đóng
                            displayProduct();
                            searchTextField.setText("");
                            showMetrics();
                        }
                        ap = null; //// Đặt lại thành null khi cửa sổ đóng
                    }
                });
            } else {
                ap.toFront(); // Đưa frame đã tồn tại lên trước mặt
            }
        }
    }//GEN-LAST:event_addProductActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        if (verifyAccess.authorizationNVBH(userRole)) {
            if (deleteProduct.isEnabled() == true) {
                if (productTable.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Chọn một sản phẩm để xóa!");
                    return;
                }
                ProductModel product = new ProductModel(key);
                ProductCtrl tmp = new ProductCtrl(DBConnection.getConnection());
                int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này?", "Alert",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    boolean success = tmp.delete(product);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Đã xóa sản phẩm");
                        displayProduct();
                        showMetrics();
                    }
                }
            }
        }
    }//GEN-LAST:event_deleteProductActionPerformed

    private void detailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailButtonActionPerformed
        if (detailButton.isEnabled() == true) {
            if (productTable.getSelectedRow() >= 0) {
                if (pd == null) {
                    pd = new ProductDetail(key);
                    pd.setVisible(true);
                    pd.setUserRole(userRole);
                    pd.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            if (pd.isDataChanged()) {
                                // Gọi phương thức cập nhật từ JFrame gốc khi JFrame mới đóng
                                showMetrics();
                                displayProduct();
                            }
                            pd = null; //Đặt lại thành null khi đóng
                        }
                    });
                } else {
                    pd.toFront();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Chọn một sản phẩm để xem!");
            }
        }
    }//GEN-LAST:event_detailButtonActionPerformed

    private void searchCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchCbItemStateChanged
        searchTextField.setText("");
    }//GEN-LAST:event_searchCbItemStateChanged

    private void exportProductToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportProductToExcelActionPerformed
        Object options[] = {"Sản phẩm còn bán được ", "Sản phẩm hết hạn sử dụng", "Tất cả sản phẩm"};
        int choice = JOptionPane.showOptionDialog(null, "Chọn kiểu xuất danh sách", "Xuất danh sách", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == JOptionPane.CLOSED_OPTION) {
            return;
        }
        ExportProductToExcel tmp = new ExportProductToExcel(DBConnection.getConnection());
        tmp.exportProductToExcel(choice);
    }//GEN-LAST:event_exportProductToExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton detailButton;
    private javax.swing.JButton exportProductToExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> productSort;
    private javax.swing.JTable productTable;
    private javax.swing.JLabel refresh;
    private javax.swing.JComboBox<String> searchCb;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel todayRevenue;
    private javax.swing.JLabel totalInvoice;
    private javax.swing.JLabel totalProduct;
    private javax.swing.JLabel totalRevenue;
    private javax.swing.JLabel totalSold;
    private javax.swing.JLabel warehouse;
    // End of variables declaration//GEN-END:variables
}
