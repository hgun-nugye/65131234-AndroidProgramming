package ntu.ntth.ontapgk;

public class BaiThuoc {
    private String ten;
    private String thoiGian;
    private int hinhAnh; // Resource ID

    public BaiThuoc(String ten, String thoiGian, int hinhAnh) {
        this.ten = ten;
        this.thoiGian = thoiGian;
        this.hinhAnh = hinhAnh;
    }

    public String getTen() {
        return ten;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
