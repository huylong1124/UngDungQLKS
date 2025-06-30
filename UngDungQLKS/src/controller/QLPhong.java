package controller;

import Model.Phong;
import static Databasee.Databasee.getConnection;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class QLPhong extends Phong {
    
    // Hàm tạo từ lớp cha Phong
    public QLPhong(String maPhong, String tenPhong, String loaiPhong, double giaPhong, String tinhTrang) {
        super(maPhong, tenPhong, loaiPhong, giaPhong, tinhTrang);
    }

    // Constructor trống (mặc định) để phục vụ cho việc tìm kiếm
    public QLPhong() {
        super();  // Gọi constructor trống của lớp Phong
    }

    // Phương thức thêm phòng vào cơ sở dữ liệu
    public boolean themPhong() {
        java.sql.Connection conn = getConnection();
        try {
            String sql = "INSERT INTO dbo.Phong (MaPhong, TenPhong, LoaiPhong, GiaPhong, TinhTrang) VALUES (?, ?, ?, ?, ?)";
            var ps = conn.prepareStatement(sql);
            
            ps.setString(1, maPhong);
            ps.setString(2, tenPhong);
            ps.setString(3, loaiPhong);
            ps.setDouble(4, giaPhong);
            ps.setString(5, tinhTrang);
            int rowsInserted = ps.executeUpdate();
            ps.close();
            conn.close();

            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức tìm kiếm phòng 
    public DefaultTableModel timKiemPhong(String loaiPhong, String tinhTrang) {
        java.sql.Connection conn = getConnection();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã phòng");
        model.addColumn("Tên phòng");
        model.addColumn("Loại Phòng");
        model.addColumn("Giá Phòng");
        model.addColumn("Tình Trạng");

        try {
            String sql = "SELECT * FROM dbo.Phong WHERE LoaiPhong LIKE ? OR TinhTrang LIKE ? "; //Tìm kiếm phòng theo tình trạng phòng
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + loaiPhong + "%");
            ps.setString(2, "%" + tinhTrang + "%");

            var rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("TenPhong"),
                    rs.getString("LoaiPhong"),
                    rs.getDouble("GiaPhong"),
                    rs.getString("TinhTrang"),});
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }

    public int demSoPhongTrong() {
        java.sql.Connection conn = getConnection();
        int soPhongTrong = 0;

        try {
            String sql = "SELECT COUNT(*) AS SoPhongTrong FROM dbo.Phong WHERE TinhTrang = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Còn Trống");

            var rs = ps.executeQuery();
            if (rs.next()) {
                soPhongTrong = rs.getInt("SoPhongTrong");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return soPhongTrong;
    }

}
