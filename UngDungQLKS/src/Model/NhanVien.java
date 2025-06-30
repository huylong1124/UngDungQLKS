package Model;
        
import javax.swing.JOptionPane;

public class NhanVien {
    protected String maNV;
    protected String hoTen;
    protected String chucVu;
    protected String sdt;
    protected String diaChi;
    protected double heSoLuong;
    
    // Hàm tạo
    public NhanVien(String maNV, String hoTen, String sdt, String diaChi, String chucVu, double heSoLuong) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        setSdt(sdt); // Thêm kiểm tra số điện thoại
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.heSoLuong = heSoLuong;
    }

    // Kiểm tra độ dài số điện thoại
private boolean kiemTraSdtHopLe(String sdt) {
    if (sdt == null || sdt.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (!sdt.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "Số điện thoại chỉ được chứa các chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (sdt.length() != 9) {
        JOptionPane.showMessageDialog(null, "Số điện thoại phải có đúng 9 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
}


    // Getter và setter
    
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
  
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        if (kiemTraSdtHopLe(sdt)) {
            this.sdt = sdt;
        } else {
            throw new IllegalArgumentException("Số điện thoại phải có đúng 9 chữ số.");
        }
    }
    
    // Constructor trống
    public NhanVien() {
    
    }  
    
    // Các getter và setter còn lại
    
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoten) {
        this.hoTen = hoten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

}

