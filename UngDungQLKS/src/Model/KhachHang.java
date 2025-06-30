package Model;

public class KhachHang {

    protected String maKH;
    protected String hoTen;
    protected String socmnd;
    protected String sdt;
    protected String diaChi;

    // Hàm tạo
    public KhachHang(String maKH, String hoTen, String socmnd, String sdt, String diaChi) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        setSdt(sdt); // Thêm kiểm tra số điện thoại
        this.socmnd = socmnd;
        this.diaChi = diaChi;
    }

    // Kiểm tra độ dài số điện thoại
    private boolean kiemTraSdtHopLe(String sdt) {
        return sdt.matches("\\d{10}"); // Kiểm tra nếu có đúng 10 chữ số
    }

    // Getter và setter
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        if (kiemTraSdtHopLe(sdt)) {
            this.sdt = sdt;
        } else {
            throw new IllegalArgumentException("Số điện thoại phải có đúng 10 chữ số.");
        }
    }

    // Constructor trống
    public KhachHang() {

    }

    // Các getter và setter còn lại
    public String getSocmnd() {
        return socmnd;
    }

    public void setSocmnd(String socmnd) {
        this.socmnd = socmnd;
    }

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

}
