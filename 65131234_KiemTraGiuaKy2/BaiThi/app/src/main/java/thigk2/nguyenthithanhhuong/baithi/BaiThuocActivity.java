package thigk2.nguyenthithanhhuong.baithi;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BaiThuocActivity extends AppCompatActivity {
    private RecyclerView rvBaiThuoc;
    private EditText etSearch;
    private TextView tvCount;
    private BaiThuocAdapter adapter;
    private ImageButton btnBack;

    private final List<BaiThuoc> danhSachGoc = new ArrayList<>();
    private final List<BaiThuoc> danhSachHien = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_thuoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.baithuoc), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getDieuKhien();
        loadData();
        setupRecyclerView();
        setupSearch();

        btnBack.setOnClickListener(view -> finish());
    }

    private void getDieuKhien() {
        rvBaiThuoc = findViewById(R.id.rv_bai_thuoc);
        etSearch = findViewById(R.id.et_search);
        tvCount = findViewById(R.id.tv_count);
        btnBack = findViewById(R.id.btn_back);
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    private void loadData() {
        danhSachGoc.add(new BaiThuoc(
                "Gừng trị cảm lạnh",
                "Giảm ho, sổ mũi, làm ấm cơ thể",
                "Gừng tươi, mật ong, chanh",
                "Đun gừng với nước sôi, thêm mật ong và vắt chanh, uống khi còn ấm.",
                R.drawable.gung, "3 – 5 ngày"
        ));
        danhSachGoc.add(new BaiThuoc(
                "Nghệ mật ong trị đau dạ dày",
                "Giảm viêm loét, bảo vệ niêm mạc dạ dày",
                "Bột nghệ, mật ong nguyên chất",
                "Trộn 1 thìa bột nghệ với 2 thìa mật ong, uống trước bữa ăn 30 phút.",
                R.drawable.nghe, "2 tuần"
        ));
        danhSachGoc.add(new BaiThuoc(
                "Lá tía tô trị ho",
                "Kháng viêm, giảm ho, long đờm hiệu quả",
                "Lá tía tô tươi, đường phèn",
                "Rửa sạch lá tía tô, đun với nước và đường phèn 15 phút, uống ngày 2 lần.",
                R.drawable.tiato, "5 – 7 ngày"
        ));
        danhSachGoc.add(new BaiThuoc(
                "Lá lốt trị đau xương khớp",
                "Giảm đau nhức, chống viêm khớp",
                "Lá lốt tươi",
                "Đun 15g lá lốt với 2 bát nước, còn 1 bát. Uống sau bữa ăn tối.",
                R.drawable.lalot, "10 ngày"
        ));
        danhSachGoc.add(new BaiThuoc(
                "Mật ong chanh trị đau họng",
                "Sát khuẩn, làm dịu cổ họng, giảm viêm",
                "Mật ong, chanh tươi, muối",
                "Pha 2 thìa mật ong + nước cốt chanh + 1 nhúm muối với nước ấm.",
                R.drawable.chanh, "3 ngày"
        ));
        danhSachGoc.add(new BaiThuoc(
                "Đinh lăng bổ khí huyết",
                "Tăng cường sức đề kháng, bổ khí huyết",
                "Rễ đinh lăng khô",
                "Sắc 20g rễ đinh lăng với 3 bát nước, còn 1 bát.",
                R.drawable.dinhlang, "1 tháng"
        ));
        danhSachGoc.add(new BaiThuoc(
                "Tỏi kháng khuẩn",
                "Kháng khuẩn tự nhiên, tăng đề kháng",
                "Tỏi tươi, mật ong",
                "Ngâm tỏi với mật ong 1 tuần.",
                R.drawable.toi, "1 tuần"
        ));
        danhSachGoc.add(new BaiThuoc(
                "Lá bạc hà trị đau đầu",
                "Giảm đau đầu, thư giãn tinh thần",
                "Lá bạc hà tươi",
                "Vò nát và xoa lên thái dương.",
                R.drawable.bacha, "Dùng khi cần"
        ));

        danhSachHien.addAll(danhSachGoc);
        updateCount();
    }

    private void setupRecyclerView() {
        adapter = new BaiThuocAdapter(danhSachHien, item -> {
            // Chuyển sang ChiTietBaiActivity
            Intent intent = new Intent(this, ChiTietBaiThuocActivity.class);
            intent.putExtra("ten", item.getTenBaiThuoc());
            intent.putExtra("cong_dung", item.getCongDung());
            intent.putExtra("nguyen_lieu", item.getNguyenLieu());
            intent.putExtra("cach_dung", item.getCachDung());
            intent.putExtra("anh", item.getAnh());
            intent.putExtra("thoi_gian", item.getThoiGian());
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        rvBaiThuoc.setLayoutManager(new LinearLayoutManager(this));
        rvBaiThuoc.setAdapter(adapter);

        // Tắt animation mặc định để dùng animation tùy chỉnh trong adapter
        rvBaiThuoc.setItemAnimator(null);
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterDanhSach(s.toString());
            }
        });
    }

    private void filterDanhSach(String query) {
        List<BaiThuoc> ketQua = new ArrayList<>();
        String q = query.toLowerCase().trim();

        if (q.isEmpty()) {
            ketQua.addAll(danhSachGoc);
        } else {
            for (BaiThuoc bt : danhSachGoc) {
                if (bt.getTenBaiThuoc().toLowerCase().contains(q)
                        || bt.getCongDung().toLowerCase().contains(q)) {
                    ketQua.add(bt);
                }
            }
        }

        danhSachHien.clear();
        danhSachHien.addAll(ketQua);
        adapter.updateList(danhSachHien);
        updateCount();
    }

    private void updateCount() {
        tvCount.setText(danhSachHien.size() + " bài thuốc");
    }
}