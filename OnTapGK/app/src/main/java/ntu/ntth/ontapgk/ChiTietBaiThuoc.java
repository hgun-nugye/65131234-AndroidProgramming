package ntu.ntth.ontapgk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChiTietBaiThuoc extends AppCompatActivity {

    ImageView img;
    TextView tvTen, tvThoiGian;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_bai_thuoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        img = findViewById(R.id.img_baithuoc);
        tvTen = findViewById(R.id.tv_ten);
        tvThoiGian = findViewById(R.id.tv_thoigian);
        btnBack = findViewById(R.id.btn_back);

        Intent intent = getIntent();
        tvTen.setText(intent.getStringExtra("ten"));
        tvThoiGian.setText(intent.getStringExtra("thoigian"));

        int hinh = intent.getIntExtra("hinh", R.drawable.medicine);
        img.setImageResource(hinh);

        btnBack.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietBaiThuoc.this, ActivityBaiThuoc.class);
                startActivity(intent);
            }
        });
    }
}