package controller;

import Model.KhachHang;
import static Databasee.Databasee.getConnection;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class QLKhachHang extends KhachHang {

    // Hàm tạo từ lớp cha NhanVien
    public QLKhachHang(String maKH, String hoTen,String socmnd, String sdt, String diaChi) {
        super(maKH, hoTen, socmnd, sdt, diaChi);
    }
    
        // Constructor trống (mặc định) để phục vụ cho việc tìm kiếm
    public QLKhachHang() {
        super();  // Gọi constructor trống của lớp KhachHang
    }

    // Phương thức thêm khách hàng vào cơ sở dữ liệu
    public boolean themKhachHang() {
        java.sql.Connection conn = getConnection();
        try {
            String sql = "INSERT INTO dbo.KhachHang (MaKH, HoTen, SoCMND, SDT, DiaChi) VALUES (?, ?, ?, ?, ?)";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, maKH);
            ps.setString(2, hoTen);
            ps.setString(3, socmnd);
            ps.setString(4, sdt);
            ps.setString(5, diaChi);            

            int rowsInserted = ps.executeUpdate();
            ps.close();
            conn.close();

            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức tìm kiếm khách hàng theo tên
    public DefaultTableModel timKiemKhachHang(String maKH) {
        java.sql.Connection conn = getConnection();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Khách Hàng");
        model.addColumn("Họ Tên");
        model.addColumn("Số CMND");
        model.addColumn("SĐT");
        model.addColumn("Địa Chỉ");   

        try {
            String sql = "SELECT * FROM dbo.KhachHang WHERE MaKH LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + maKH + "%");

            var rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MaKH"),
                    rs.getString("HoTen"),
                    rs.getString("SoCMND"),
                    rs.getString("SDT"),
                    rs.getString("DiaChi"),                            
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
