package thigk2.nguyenthithanhhuong.baithi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChiTietMonAnActivity extends AppCompatActivity {
    private ImageButton btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mon_an);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chitietmonan), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Nhận dữ liệu từ MonAnActivity
        String tenMon = getIntent().getStringExtra("ten_mon");
        String moTa   = getIntent().getStringExtra("mo_ta");
        String calo   = getIntent().getStringExtra("calo");
        int anh   = getIntent().getIntExtra("anh", R.drawable.bg_icon_food);

        // Gắn dữ liệu vào view
        ((ImageView) findViewById(R.id.img_food)).setImageResource(anh);
        ((TextView) findViewById(R.id.tv_ten_mon_detail)).setText(tenMon);
        ((TextView) findViewById(R.id.tv_mo_ta_detail)).setText(moTa);
        ((TextView) findViewById(R.id.tv_calo_detail)).setText(calo);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        Toast.makeText(this, "🍽️ " + tenMon, Toast.LENGTH_SHORT).show();

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
    }
}