package Model;

public class BaoCaoThongKe {
    protected String maBaoCao;
    protected String ngayBaoCao;
    protected String loaiBaoCao;
    protected int tongSoHoaDon;
    protected int soLuongTon;
    protected double tongDoanhThu;
    protected String moTa;

    // Hàm tạo đầy đủ
    public BaoCaoThongKe(String maBaoCao, String ngayBaoCao, String loaiBaoCao, int tongSoHoaDon, int soLuongTon, double tongDoanhThu, String moTa) {
        this.maBaoCao = maBaoCao;
        this.ngayBaoCao = ngayBaoCao;
        this.loaiBaoCao = loaiBaoCao;
        this.tongSoHoaDon = tongSoHoaDon;
        this.soLuongTon = soLuongTon;
        this.tongDoanhThu = tongDoanhThu;
        this.moTa = moTa;
    }

    // Constructor trống
    public BaoCaoThongKe() {
    }

    // Getter và Setter cho từng trường
    public String getMaBaoCao() {
        return maBaoCao;
    }

    public void setMaBaoCao(String maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public String getNgayBaoCao() {
        return ngayBaoCao;
    }

    public void setNgayBaoCao(String ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
    }

    public String getLoaiBaoCao() {
        return loaiBaoCao;
    }

    public void setLoaiBaoCao(String loaiBaoCao) {
        this.loaiBaoCao = loaiBaoCao;
    }

    public int getTongSoHoaDon() {
        return tongSoHoaDon;
    }

    public void setTongSoHoaDon(int tongSoHoaDon) {
        if (tongSoHoaDon >= 0) {
            this.tongSoHoaDon = tongSoHoaDon;
        } else {
            throw new IllegalArgumentException("Tổng số hóa đơn không thể âm.");
        }
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        if (soLuongTon >= 0) {
            this.soLuongTon = soLuongTon;
        } else {
            throw new IllegalArgumentException("Số lượng tồn không thể âm.");
        }
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        if (tongDoanhThu >= 0) {
            this.tongDoanhThu = tongDoanhThu;
        } else {
            throw new IllegalArgumentException("Tổng doanh thu không thể âm.");
        }
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
