package controller;

import Model.DichVuSuDung;
import static Databasee.Databasee.getConnection;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class QLDichVuSuDung extends DichVuSuDung {

    // Hàm tạo từ lớp cha NhanVien
    public QLDichVuSuDung(String maDV, String tenDV, String loaiDichVu, double donGia, String soLuongTon, String moTa) {
        super(maDV, tenDV, loaiDichVu, donGia, soLuongTon, moTa);
    }

    // Constructor trống (mặc định) để phục vụ cho việc tìm kiếm
    public QLDichVuSuDung() {
        super();  // Gọi constructor trống của lớp NhanVien
    }

    // Phương thức thêm dịch vụ vào cơ sở dữ liệu
    public boolean themDichVu() {
        java.sql.Connection conn = getConnection();
        try {
            String sql = "INSERT INTO dbo.DichVu (MaDV, TenDV, LoaiDichVu, DonGia, SoLuongTon, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, maDV);
            ps.setString(2, tenDV);
            ps.setString(3, loaiDichVu);
            ps.setDouble(4, donGia);
            ps.setString(5, soLuongTon);
            ps.setString(6, moTa);        

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
    public DefaultTableModel timKiemDichVu(String maDV) {
        java.sql.Connection conn = getConnection();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Dịch Vụ");
        model.addColumn("Tên Dịch Vụ");
        model.addColumn("Loại Dịch Vụ");
        model.addColumn("Đơn Giá");
        model.addColumn("Số Lượng Tồn");
        model.addColumn("Mô Tả");

        try {
            String sql = "SELECT * FROM dbo.DichVu WHERE MaDV LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + maDV + "%");

            var rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MaDV"),
                    rs.getString("TenDV"),
                    rs.getString("LoaiDichVu"),
                    rs.getString("DonGia"),
                    rs.getString("SoLuongTon"),
                    rs.getString("MoTa"),});
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
