package thigk2.nguyenthithanhhuong.a65131234_kiemtragk2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CaKhucCachMangActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvTitle;
    private ListView lvCaKhuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_khuc_cach_mang);

        getDieuKhien();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String[] danhSachCaKhuc = {
                "Tiến Quân Ca",
                "Như Có Bác Hồ Trong Ngày Vui Đại Thắng",
                "Cô Gái Mở Đường",
                "Đất Nước Trọn Niềm Vui",
                "Bác Đang Cùng Chúng Cháu Hành Quân",
                "Nguyễn Thị Thanh Hương",
                "Đất Nước Trọn Niềm Vui",
                "Bác Đang Cùng Chúng Cháu Hành Quân",
                "Cô Gái Mở Đường",
                "Cô Gái Mở Đường"
        };

        android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                danhSachCaKhuc
        );

        lvCaKhuc.setAdapter(adapter);

        // Xử lý sự kiện click item
        lvCaKhuc.setOnItemClickListener((parent, view, position, id) -> {
            String tenBaiHat = danhSachCaKhuc[position];
            Toast.makeText(CaKhucCachMangActivity.this, "Bạn chọn: " + tenBaiHat, Toast.LENGTH_SHORT).show();
        });
    }

    public void getDieuKhien() {
        // Ánh xạ view
        btnBack = findViewById(R.id.btn_back);
        tvTitle = findViewById(R.id.tv_screen_title);
        lvCaKhuc = findViewById(R.id.lv_ca_khuc);
    }

}
