package ntu.bt8.tuychinhlv;

public class MonAn {
    private String tenMonAn;
    private double donGia;
    private String moTa;
    private int idAnhMinhHoa;

    public MonAn(String tenMonAn, double donGia, String moTa, int idAnhMinhHoa) {
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.moTa = moTa;
        this.idAnhMinhHoa = idAnhMinhHoa;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public double getDonGia() {
        return donGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getIdAnhMinhHoa() {
        return idAnhMinhHoa;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setIdAnhMinhHoa(int idAnhMinhHoa) {
        this.idAnhMinhHoa = idAnhMinhHoa;
    }
}
