/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Databasee.Databasee;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author Admin
 */
public class frmBieuDoThongKe extends javax.swing.JFrame {

    private JPanel pnlBieuDo;
    private Connection conn;

    /**
     * Creates new form frmBieuDoThongKe
     */
    public frmBieuDoThongKe() {
        initComponents();
        conn = Databasee.getConnection(); // Dùng lớp bạn đã tạo
        hienThiBieuDo();
    }

    private void hienThiBieuDo() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(bieuDoCot_DoanhThuTheoNgay());
        panel.add(bieuDoCot_DoanhThuTheoNam());
        panel.add(bieuDoTron_TrangThaiHoaDon());
        setContentPane(panel);
        validate(); // refresh lại UI
    }

    private ChartPanel bieuDoCot_DoanhThuTheoNgay() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            String sql = "SELECT NgayThanhToan, SUM(TongTien) AS TongTienNgay FROM HoaDon WHERE TrangThai = 'Đã thanh toán' GROUP BY NgayThanhToan ORDER BY NgayThanhToan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ngay = rs.getString("NgayThanhToan");
                double tongTien = rs.getDouble("TongTienNgay");
                dataset.addValue(tongTien, "Doanh thu", ngay);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo ngày", "Ngày Thanh Toán", "Tổng tiền",
                dataset);

        return new ChartPanel(chart);
    }

    private ChartPanel bieuDoTron_TrangThaiHoaDon() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            String sql = "SELECT TrangThai, COUNT(*) AS SoLuong FROM HoaDon GROUP BY TrangThai";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String trangThai = rs.getString("TrangThai");
                if (trangThai == null || trangThai.trim().isEmpty()) {
                    trangThai = "Không rõ";
                }
                int soLuong = rs.getInt("SoLuong");
                dataset.setValue(trangThai, soLuong);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Tỷ lệ trạng thái hóa đơn", dataset, true, true, false);

        return new ChartPanel(chart);
    }

    public void loadDuLieuThongKe() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Connection conn = Databasee.getConnection();
            String sql = "SELECT NgayThanhToan, SUM(TongTien) AS DoanhThu FROM HoaDon "
                    + "WHERE TrangThai = 'Đã thanh toán' GROUP BY NgayThanhToan ORDER BY NgayThanhToan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date ngay = rs.getDate("NgayThanhToan");
                double doanhThu = rs.getDouble("DoanhThu");
                dataset.addValue(doanhThu, "Doanh thu", ngay.toString());
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    "Biểu đồ doanh thu theo ngày",
                    "Ngày",
                    "Doanh thu",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(700, 400));
            pnlBieuDo.removeAll();
            pnlBieuDo.setLayout(new BorderLayout());
            pnlBieuDo.add(chartPanel, BorderLayout.CENTER);
            pnlBieuDo.validate();

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ChartPanel bieuDoCot_DoanhThuTheoNam() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            String sql = "SELECT YEAR(NgayThanhToan) AS Nam, SUM(TongTien) AS TongTienNam "
                    + "FROM HoaDon WHERE TrangThai = 'Đã thanh toán' "
                    + "GROUP BY YEAR(NgayThanhToan) ORDER BY Nam";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nam = rs.getString("Nam");
                double tongTien = rs.getDouble("TongTienNam");
                dataset.addValue(tongTien, "Doanh thu", nam);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo năm", "Năm", "Tổng tiền",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        return new ChartPanel(chart);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmBieuDoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBieuDoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBieuDoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBieuDoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBieuDoThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
