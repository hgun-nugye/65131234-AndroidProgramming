package ntu.ntth.ontapgk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityBaiThuoc extends AppCompatActivity {
    RecyclerView rvBaiThuoc;
    MaterialButton btnBack;
    BaiThuocAdapter adapter;
    List<BaiThuoc> dsBaiThuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_thuoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvBaiThuoc = findViewById(R.id.rv_baithuoc);
        btnBack = findViewById(R.id.btn_back);

        dsBaiThuoc = new ArrayList<>();
        dsBaiThuoc.add(new BaiThuoc("Sâm Quy Đại Quả", "45 phút", R.drawable.samquy));
        dsBaiThuoc.add(new BaiThuoc("Bát Trân Thang", "60 phút", R.drawable.medicine));
        dsBaiThuoc.add(new BaiThuoc("Lục Vị Địa Hoàng", "30 phút", R.drawable.medicine));
        dsBaiThuoc.add(new BaiThuoc("An Cung Ngưu Hoàng", "15 phút", R.drawable.medicine));
        dsBaiThuoc.add(new BaiThuoc("Tiêu Dao Tán", "40 phút", R.drawable.medicine));
        dsBaiThuoc.add(new BaiThuoc("Độc Hoạt Tang Ký Sinh", "50 phút", R.drawable.medicine));
        dsBaiThuoc.add(new BaiThuoc("Quy Tỳ Thang", "45 phút", R.drawable.quytythang));
        dsBaiThuoc.add(new BaiThuoc("Ma Hoàng Thang", "20 phút", R.drawable.medicine));

        adapter = new BaiThuocAdapter(dsBaiThuoc, item -> {
            Intent intent = new Intent(ActivityBaiThuoc.this, ChiTietBaiThuoc.class);
            intent.putExtra("ten", item.getTen());
            intent.putExtra("thoigian", item.getThoiGian());
            intent.putExtra("hinh", item.getHinhAnh());
            startActivity(intent);
            Toast.makeText(ActivityBaiThuoc.this, "Bạn chọn: " + item.getTen(), Toast.LENGTH_SHORT).show();
        });

        rvBaiThuoc.setLayoutManager(new LinearLayoutManager(this));
        rvBaiThuoc.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}