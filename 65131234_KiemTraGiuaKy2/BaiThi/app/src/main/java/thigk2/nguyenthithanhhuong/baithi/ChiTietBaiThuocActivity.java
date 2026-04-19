package thigk2.nguyenthithanhhuong.baithi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietBaiThuocActivity extends AppCompatActivity {
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_bai_thuoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chitietbaithuoc), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String ten = getIntent().getStringExtra("ten");
        String congDung = getIntent().getStringExtra("cong_dung");
        String nguyenLieu = getIntent().getStringExtra("nguyen_lieu");
        String cachDung = getIntent().getStringExtra("cach_dung");
        int anh = getIntent().getIntExtra("anh", R.drawable.bg_icon_medicine);
        String thoiGian = getIntent().getStringExtra("thoi_gian");

        ((ImageView) findViewById(R.id.img_medicine)).setImageResource(anh);
        ((TextView) findViewById(R.id.tv_ten_detail)).setText(ten);
        ((TextView) findViewById(R.id.tv_cong_dung_detail)).setText(congDung);
        ((TextView) findViewById(R.id.tv_nguyen_lieu_detail)).setText(nguyenLieu);
        ((TextView) findViewById(R.id.tv_cach_dung_detail)).setText(cachDung);
        ((TextView) findViewById(R.id.tv_thoi_gian_detail)).setText("⏱ " + thoiGian);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        Toast.makeText(this, "🌿 " + ten, Toast.LENGTH_SHORT).show();
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(view -> finish());
    }
}