package controller;

import Model.HoaDon;
import static Databasee.Databasee.getConnection;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class QuanLyHoaDon extends HoaDon {

    // Hàm tạo từ lớp cha NhanVien
    public QuanLyHoaDon(String maHD, String maKH, String maNV, String ngayLap, double tongTien) {
        super(maHD, maKH, maNV, ngayLap, tongTien);
    }
    
        // Constructor trống (mặc định) để phục vụ cho việc tìm kiếm
    public QuanLyHoaDon() {
        super();  // Gọi constructor trống của lớp NhanVien
    }

    // Phương thức thêm nhân viên vào cơ sở dữ liệu
    public boolean themHoaDon() {
        java.sql.Connection conn = getConnection();
        try {
            String sql = "INSERT INTO dbo.HoaDon (MaHD, MaKH, MaNV, NgayLap, TongTien) VALUES (?, ?, ?, ?, ?)";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, maHD);
            ps.setString(2, maKH);
            ps.setString(3, maNV);
            ps.setString(4, ngayLap);
            ps.setDouble(5, tongTien);          

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
    public DefaultTableModel timKiemHoaDon(String maHD) {
        java.sql.Connection conn = getConnection();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Hóa Đơn");
        model.addColumn("Mã Khách Hàng");
        model.addColumn("Mã Nhân Viên");
        model.addColumn("Ngày Lập");
        model.addColumn("Tổng Tiền");
    
        try {
            String sql = "SELECT * FROM dbo.HoaDon WHERE MaHD LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + maHD + "%");

            var rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MaHD"),
                    rs.getString("MaKH"),
                    rs.getString("MaNV"),
                    rs.getString("NgayLap"),
                    rs.getDouble("TongTien"),                  
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
