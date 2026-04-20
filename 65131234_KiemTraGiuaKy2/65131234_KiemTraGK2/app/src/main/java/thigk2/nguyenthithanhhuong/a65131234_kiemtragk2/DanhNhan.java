package thigk2.nguyenthithanhhuong.a65131234_kiemtragk2;

import androidx.recyclerview.widget.RecyclerView;

public class DanhNhan{
    private int anh;
    private String ten, quequan ;

    public DanhNhan(int anh, String ten, String quequan) {
        this.anh = anh;
        this.ten = ten;
        this.quequan = quequan;
    }

    public int getAnh() {
        return anh;
    }

    public String getTen() {
        return ten;
    }

    public String getQuequan() {
        return quequan;
    }
}
