package thigk2.nguyenthithanhhuong.a65131234_kiemtragk2;

import static android.app.ProgressDialog.show;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DanhNhanVietNamActivity extends AppCompatActivity {
    private RecyclerView reDanhNhan;
    private DanhNhanAdapter adapter;
    private ImageButton btnBack;

    private final List<DanhNhan> danhSach = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_nhan_viet_nam);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.danhnhan), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getDieuKhien();
        loadData();

        btnBack.setOnClickListener(view -> finish());

        reDanhNhan.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DanhNhanAdapter(danhSach, item -> {
            Toast.makeText(DanhNhanVietNamActivity.this,
                    "Bạn chọn: " + item.getTen(),
                    Toast.LENGTH_SHORT).show();
        });

        reDanhNhan.setAdapter(adapter);
    }


    public void getDieuKhien() {
        reDanhNhan = findViewById(R.id.rv_danhnhan);
        btnBack = findViewById(R.id.btn_back);
    }

    public void loadData() {
        danhSach.add(new DanhNhan(R.drawable.profile, "Nguyễn Thị Thanh Hương", "Hà Nội"));
        danhSach.add(new DanhNhan(R.drawable.profile, "Nguyễn Thị Thanh Hương", "Hà Nội"));
        danhSach.add(new DanhNhan(R.drawable.profile, "Nguyễn Thị Thanh Hương", "Hà Nội"));
        danhSach.add(new DanhNhan(R.drawable.profile, "Nguyễn Thị Thanh Hương", "Hà Nội"));
        danhSach.add(new DanhNhan(R.drawable.profile, "Nguyễn Thị Thanh Hương", "Hà Nội"));
    }

}