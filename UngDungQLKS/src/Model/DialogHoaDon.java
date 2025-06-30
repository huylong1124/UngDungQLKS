package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DialogHoaDon extends JDialog {

    private JLabel lblMaHD, lblTenKH, lblNgayLap, lblTongTien;
    private JTable tableChiTiet;

    // Constructor nhận dữ liệu từ frmQLHoaDon
    public DialogHoaDon(Frame parent, boolean modal, String maHD, String tenKH, String ngayLap, String tongTien, List<String[]> chiTiet) {
        super(parent, modal);
        setTitle("Chi tiết hóa đơn");
        setSize(400, 500);
        setLocationRelativeTo(parent);

        // Giao diện hiển thị thông tin hóa đơn
        JPanel panelThongTin = new JPanel(new GridLayout(5, 2, 5, 5));
        panelThongTin.add(new JLabel("Mã hóa đơn:"));
        lblMaHD = new JLabel(maHD);
        panelThongTin.add(lblMaHD);

        panelThongTin.add(new JLabel("Tên khách hàng:"));
        lblTenKH = new JLabel(tenKH);
        panelThongTin.add(lblTenKH);

        panelThongTin.add(new JLabel("Ngày lập:"));
        lblNgayLap = new JLabel(ngayLap);
        panelThongTin.add(lblNgayLap);

        panelThongTin.add(new JLabel("Tổng tiền:"));
        lblTongTien = new JLabel(tongTien);
        panelThongTin.add(lblTongTien);

        add(panelThongTin, BorderLayout.NORTH);

        // Bảng chi tiết hóa đơn
        tableChiTiet = new JTable(new DefaultTableModel(new Object[]{"Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"}, 0));
        JScrollPane scrollPane = new JScrollPane(tableChiTiet);
        add(scrollPane, BorderLayout.CENTER);

        // Nút đóng
        JButton btnDong = new JButton("Đóng");
        btnDong.addActionListener(e -> dispose());
        add(btnDong, BorderLayout.SOUTH);

        // Hiển thị chi tiết hóa đơn
        DefaultTableModel model = (DefaultTableModel) tableChiTiet.getModel();
        for (String[] row : chiTiet) {
            model.addRow(row);
        }
    }
}
