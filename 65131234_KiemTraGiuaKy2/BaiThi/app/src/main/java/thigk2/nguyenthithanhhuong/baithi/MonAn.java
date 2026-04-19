package thigk2.nguyenthithanhhuong.baithi;

public class MonAn {
    private String tenMon;
    private String moTa;
    private String calo;
    private int anh;

    public MonAn(String tenMon, String moTa, String calo, int anh) {
        this.tenMon = tenMon;
        this.moTa   = moTa;
        this.calo   = calo;
        this.anh   = anh;
    }

    public String getTenMon() { return tenMon; }
    public String getMoTa()   { return moTa; }
    public String getCalo()   { return calo; }
    public int getAnh()   { return anh; }
}
