package app.storemanagement;

import app.storemanagement.view.Category;
import app.storemanagement.view.Customer;
import app.storemanagement.view.Employee;
import app.storemanagement.view.Overview;
import app.storemanagement.view.Product;
import app.storemanagement.view.Sell;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung Pham
 */
public class Dashboard extends javax.swing.JFrame {

    Product productPanel = new Product();
    Employee employeePanel = new Employee();
    Customer customerPanel = new Customer();
    Overview overviewPanel = new Overview();
    Category categoryPanel = new Category();
    Sell sellPanel = new Sell();
    private int uid;
    private String userRole;

    public Dashboard(int uid, String role) {
        initComponents();
        this.userRole = role;
        this.uid = uid;
        cardLayout = (CardLayout) (jPanel1.getLayout());
        jPanel1.add(productPanel, "product");
        jPanel1.add(employeePanel, "employee");
        jPanel1.add(customerPanel, "customer");
        jPanel1.add(overviewPanel, "overview");
        jPanel1.add(categoryPanel, "category");
        jPanel1.add(sellPanel, "sell");
        cardLayout.show(jPanel1, "overview");
        productPanel.setUserRole(role);
        categoryPanel.setUserRole(role);
        sellPanel.setUid(uid);
        switch (userRole) {
            case "NVBH" ->
                employeeBtn.setVisible(false);
            case "NVK" -> {
                employeeBtn.setVisible(false);
                customerBtn.setVisible(false);
                sellBtn.setVisible(false);
                invoiceBtn.setVisible(false);
            }
            case "admin" ->
                sellBtn.setVisible(false);
            default -> {
            }
        }
        overviewBtn.setForeground(Color.black);
        overviewBtn.setBackground(Color.decode("#F2F2F2"));

        // Tạo ActionListener chung
        ActionListener actionListener = (ActionEvent e) -> {
            overviewBtn.setBackground(Color.decode("#4C956C"));
            employeeBtn.setBackground(Color.decode("#4C956C"));
            categoryBtn.setBackground(Color.decode("#4C956C"));
            productBtn.setBackground(Color.decode("#4C956C"));
            customerBtn.setBackground(Color.decode("#4C956C"));
            sellBtn.setBackground(Color.decode("#4C956C"));
            invoiceBtn.setBackground(Color.decode("#4C956C"));

            overviewBtn.setForeground(Color.WHITE);
            employeeBtn.setForeground(Color.WHITE);
            categoryBtn.setForeground(Color.WHITE);
            productBtn.setForeground(Color.WHITE);
            customerBtn.setForeground(Color.WHITE);
            sellBtn.setForeground(Color.WHITE);
            invoiceBtn.setForeground(Color.WHITE);

            JButton source = (JButton) e.getSource();
            source.setBackground(Color.decode("#F2F2F2"));
            source.setForeground(Color.black);
        };

        // Thêm ActionListener vào mỗi nút
        overviewBtn.addActionListener(actionListener);
        employeeBtn.addActionListener(actionListener);
        categoryBtn.addActionListener(actionListener);
        productBtn.addActionListener(actionListener);
        customerBtn.addActionListener(actionListener);
        sellBtn.addActionListener(actionListener);
        invoiceBtn.addActionListener(actionListener);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát chứ?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    if (!sellPanel.isCartEmpty()) {
                        sellPanel.clearCart();
                    }
                    // Thực hiện hành động khi người dùng đồng ý đóng cửa sổ
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    // Ngăn chặn cửa sổ từ việc đóng
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    CardLayout cardLayout = new CardLayout();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        infoLabel = new javax.swing.JLabel();
        overviewBtn = new javax.swing.JButton();
        employeeBtn = new javax.swing.JButton();
        categoryBtn = new javax.swing.JButton();
        productBtn = new javax.swing.JButton();
        customerBtn = new javax.swing.JButton();
        sellBtn = new javax.swing.JButton();
        invoiceBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý cửa hàng");
        setFocusable(false);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 720));
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(76, 149, 108));
        jPanel2.setForeground(new java.awt.Color(76, 149, 108));

        infoLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(255, 255, 255));
        infoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        infoLabel.setText("Xin chào, username");

        overviewBtn.setBackground(new java.awt.Color(76, 149, 108));
        overviewBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        overviewBtn.setForeground(new java.awt.Color(255, 255, 255));
        overviewBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/statistics.png"))); // NOI18N
        overviewBtn.setText("   Tổng quan");
        overviewBtn.setBorder(null);
        overviewBtn.setFocusable(false);
        overviewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overviewBtnActionPerformed(evt);
            }
        });

        employeeBtn.setBackground(new java.awt.Color(76, 149, 108));
        employeeBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        employeeBtn.setForeground(new java.awt.Color(255, 255, 255));
        employeeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/employees.png"))); // NOI18N
        employeeBtn.setText("   Nhân viên");
        employeeBtn.setBorder(null);
        employeeBtn.setFocusable(false);
        employeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeBtnActionPerformed(evt);
            }
        });

        categoryBtn.setBackground(new java.awt.Color(76, 149, 108));
        categoryBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        categoryBtn.setForeground(new java.awt.Color(255, 255, 255));
        categoryBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categories.png"))); // NOI18N
        categoryBtn.setText("   Phân loại");
        categoryBtn.setBorder(null);
        categoryBtn.setFocusable(false);
        categoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBtnActionPerformed(evt);
            }
        });

        productBtn.setBackground(new java.awt.Color(76, 149, 108));
        productBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        productBtn.setForeground(new java.awt.Color(255, 255, 255));
        productBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/products.png"))); // NOI18N
        productBtn.setText("   Sản phẩm");
        productBtn.setBorder(null);
        productBtn.setFocusable(false);
        productBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productBtnActionPerformed(evt);
            }
        });

        customerBtn.setBackground(new java.awt.Color(76, 149, 108));
        customerBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        customerBtn.setForeground(new java.awt.Color(255, 255, 255));
        customerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/customer.png"))); // NOI18N
        customerBtn.setText("   Khách hàng");
        customerBtn.setBorder(null);
        customerBtn.setFocusable(false);
        customerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBtnActionPerformed(evt);
            }
        });

        sellBtn.setBackground(new java.awt.Color(76, 149, 108));
        sellBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sellBtn.setForeground(new java.awt.Color(255, 255, 255));
        sellBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cross-selling.png"))); // NOI18N
        sellBtn.setText("   Bán hàng");
        sellBtn.setBorder(null);
        sellBtn.setFocusable(false);
        sellBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellBtnActionPerformed(evt);
            }
        });

        invoiceBtn.setBackground(new java.awt.Color(76, 149, 108));
        invoiceBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        invoiceBtn.setForeground(new java.awt.Color(255, 255, 255));
        invoiceBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/invoice.png"))); // NOI18N
        invoiceBtn.setText("   Hóa đơn");
        invoiceBtn.setBorder(null);
        invoiceBtn.setFocusable(false);
        invoiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceBtnActionPerformed(evt);
            }
        });

        logoutBtn.setBackground(new java.awt.Color(76, 149, 108));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        logoutBtn.setText("Đăng xuất");
        logoutBtn.setBorder(null);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(infoLabel))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(overviewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(categoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(productBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(customerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(sellBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(invoiceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(employeeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(infoLabel)
                .addGap(6, 6, 6)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(overviewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(categoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(productBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(customerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(sellBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(invoiceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(employeeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void productBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productBtnActionPerformed
        cardLayout.show(jPanel1, "product");
    }//GEN-LAST:event_productBtnActionPerformed

    private void overviewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overviewBtnActionPerformed
        cardLayout.show(jPanel1, "overview");
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                overviewPanel.refreshData();
            }
        },
                500
        );
    }//GEN-LAST:event_overviewBtnActionPerformed

    private void invoiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_invoiceBtnActionPerformed

    private void categoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBtnActionPerformed
        cardLayout.show(jPanel1, "category");
    }//GEN-LAST:event_categoryBtnActionPerformed

    private void customerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBtnActionPerformed
        cardLayout.show(jPanel1, "customer");
    }//GEN-LAST:event_customerBtnActionPerformed

    private void sellBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellBtnActionPerformed
        cardLayout.show(jPanel1, "sell");
    }//GEN-LAST:event_sellBtnActionPerformed

    private void employeeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeBtnActionPerformed
        cardLayout.show(jPanel1, "employee");
    }//GEN-LAST:event_employeeBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        int confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            if (!sellPanel.isCartEmpty()) {
                sellPanel.clearCart();
            }
            this.dispose();
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    public void setInfoLabel(String user) {
        this.infoLabel.setText("Xin chào, " + user);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton categoryBtn;
    private javax.swing.JButton customerBtn;
    private javax.swing.JButton employeeBtn;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JButton invoiceBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton overviewBtn;
    private javax.swing.JButton productBtn;
    private javax.swing.JButton sellBtn;
    // End of variables declaration//GEN-END:variables
}
