package ntu.bt.recycle_layout;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SanPham extends AppCompatActivity {
    public String ten;
    public String moTa;
    public String gia;
    public int anhMH;

    public SanPham(String ten, String moTa, String gia, int anhMH) {
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
        this.anhMH = anhMH;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_san_pham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.spLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}