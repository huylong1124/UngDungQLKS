package controller;

import Model.BaoCaoThongKe;
import static Databasee.Databasee.getConnection;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class QLBaoCaoThongKe extends BaoCaoThongKe {

    // Hàm tạo từ lớp cha NhanVien
    public QLBaoCaoThongKe(String maBaoCao, String ngayBaoCao, String loaiBaoCao, int tongSoHoaDon, int soLuongTon, double tongDoanhThu, String moTa) {
        super(maBaoCao, ngayBaoCao, loaiBaoCao, tongSoHoaDon, soLuongTon, tongDoanhThu, moTa);
    }
    
        // Constructor trống (mặc định) để phục vụ cho việc tìm kiếm
    public QLBaoCaoThongKe() {
        super();  // Gọi constructor trống của lớp NhanVien
    }

    // Phương thức thêm nhân viên vào cơ sở dữ liệu
    public boolean themBaoCao() {
        java.sql.Connection conn = getConnection();
        try {
            String sql = "INSERT INTO dbo.BaoCaoThongKe (MaBaoCao, NgayBaoCao, LoaiBaoCao, TongSoHoaDon, SoLuongTon, TongDoanhThu, MoTa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            var ps = conn.prepareStatement(sql);

            ps.setString(1, maBaoCao);
            ps.setString(2, ngayBaoCao);
            ps.setString(3, loaiBaoCao);
            ps.setInt(4, tongSoHoaDon);
            ps.setInt(5, soLuongTon);
            ps.setDouble(6, tongDoanhThu);
            ps.setString(7, moTa);     

            int rowsInserted = ps.executeUpdate();
            ps.close();
            conn.close();

            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức tìm kiếm nhân viên theo tên
    public DefaultTableModel timKiemBaoCao(String maBaoCao) {
        java.sql.Connection conn = getConnection();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Báo Cáo");
        model.addColumn("Ngày Báo Cáo");
        model.addColumn("Loại Báo Cáo");
        model.addColumn("Tổng Số Hóa Đơn");
        model.addColumn("Số Lượng Tồn");
        model.addColumn("Tổng Doanh Thu");
        model.addColumn("Mô Tả");      

        try {
            String sql = "SELECT * FROM dbo.BaoCaoThongKe WHERE MaBaoCao LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + maBaoCao + "%");

            var rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MaBaoCao"),
                    rs.getString("NgayBaoCao"),
                    rs.getString("LoaiBaoCao"),
                    rs.getString("TongSoHoaDon"),
                    rs.getString("SoLuongTon"),
                    rs.getString("TongDoanhThu"),
                    rs.getString("MoTa"),            
                });
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }

}
