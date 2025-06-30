package Model;

public class Phong {

    protected String maPhong;
    protected String tenPhong;
    protected String loaiPhong;
    protected double giaPhong;
    protected String tinhTrang;

    // Constructor mặc định
    public Phong() {
    }

    // Constructor đầy đủ
    public Phong(String maPhong, String tenPhong, String loaiPhong, double giaPhong, String tinhTrang) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.loaiPhong = loaiPhong;
        this.tinhTrang = tinhTrang;
        this.giaPhong = giaPhong;
    }

    // Getter và Setter
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

}
