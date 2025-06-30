package Model;

public class DichVuSuDung {

    // Thuộc tính
    protected String maDV;       //Mã dịch vụ
    protected String tenDV;      // Tên dịch vụ
    protected String loaiDichVu; // Loại dịch vụ
    protected double donGia;     // Đơn giá
    protected String soLuongTon;    // Số lượng tồn kho
    protected String moTa;       // Mô tả

    // Hàm tạo không tham số
    public DichVuSuDung() {
    }

    // Hàm tạo đầy đủ tham số
    public DichVuSuDung(String maDV, String tenDV, String loaiDichVu, double donGia, String soLuongTon, String moTa) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.loaiDichVu = loaiDichVu;
        this.donGia = donGia;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
    }

    // Getter và Setter
    
    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    
    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Đơn giá không được nhỏ hơn 0.");
        }
        this.donGia = donGia;
    }

    public String getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(String soLuongTon) {    
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
