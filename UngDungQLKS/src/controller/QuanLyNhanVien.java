package controller;

import Model.NhanVien;
import static Databasee.Databasee.getConnection;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class QuanLyNhanVien extends NhanVien {

    // Hàm tạo từ lớp cha NhanVien
    public QuanLyNhanVien(String maNV, String hoTen, String chucVu, String sdt, String diaChi,  double heSoLuong) {
        super(maNV, hoTen, chucVu, sdt, diaChi,  heSoLuong);
    }
    
        // Constructor trống (mặc định) để phục vụ cho việc tìm kiếm
    public QuanLyNhanVien() {
        super();  // Gọi constructor trống của lớp NhanVien
    }

    // Phương thức thêm nhân viên vào cơ sở dữ liệu
    public boolean themNhanVien() {
        java.sql.Connection conn = getConnection();
        try {
            String sql = "INSERT INTO dbo.NhanVien (MaNV, HoTen, ChucVu, SDT, DiaChi, HeSoLuong) VALUES (?, ?, ?, ?, ?, ?)";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, hoTen);
            ps.setString(3, chucVu);
            ps.setString(4, sdt);
            ps.setString(5, diaChi);    
            ps.setDouble(6, heSoLuong);

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
    public DefaultTableModel timKiemNhanVien(String maNV) {
        java.sql.Connection conn = getConnection();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Nhân Viên");
        model.addColumn("Họ Tên");
        model.addColumn("Chức Vụ");
        model.addColumn("SĐT");
        model.addColumn("Địa Chỉ");   
        model.addColumn("Hệ Số Lương");

        try {
            String sql = "SELECT * FROM dbo.NhanVien WHERE ChucVu LIKE ?"; //Tìm kiếm nhân viên theo chức vụ
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + maNV + "%");

            var rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("ChucVu"),
                    rs.getString("SDT"),
                    rs.getString("DiaChi"),          
                    rs.getDouble("HeSoLuong"),                
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
