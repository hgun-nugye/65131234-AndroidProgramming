package thigk2.nguyenthithanhhuong.baithi;

public class BaiThuoc {
    private String tenBaiThuoc;
    private String congDung;
    private String nguyenLieu;
    private String cachDung;
    private int anh;
    private String thoiGian;

    public BaiThuoc(String tenBaiThuoc, String congDung, String nguyenLieu, String cachDung, int anh, String thoiGian) {
        this.tenBaiThuoc = tenBaiThuoc;
        this.congDung    = congDung;
        this.nguyenLieu  = nguyenLieu;
        this.cachDung    = cachDung;
        this.anh        = anh;
        this.thoiGian    = thoiGian;
    }

    public String getTenBaiThuoc() { return tenBaiThuoc; }
    public String getCongDung()    { return congDung; }
    public String getNguyenLieu()  { return nguyenLieu; }
    public String getCachDung()    { return cachDung; }
    public int getAnh()        { return anh; }
    public String getThoiGian()    { return thoiGian; }
}