package thigk2.nguyenthithanhhuong.baithi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MonAnActivity extends AppCompatActivity {

    private ListView lvMonAn;
    private EditText etSearch;
    private TextView tvCount;
    private MonAnAdapter adapter;
    private ImageButton btnBack;

    private List<MonAn> danhSachGoc = new ArrayList<>();
    private List<MonAn> danhSachHien = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.monan), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getDieuKhien();
        loadData();
        setupListView();
        setupSearch();

        btnBack.setOnClickListener(v->finish());
    }

    private void getDieuKhien() {
        lvMonAn = findViewById(R.id.lv_mon_an);
        etSearch = findViewById(R.id.et_search);
        tvCount = findViewById(R.id.tv_count);
        btnBack = findViewById(R.id.btn_back);
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    private void loadData() {
        danhSachGoc.add(new MonAn("Phở bò", "Món ăn truyền thống Việt Nam với nước dùng đậm đà", "450 kcal", R.drawable.pho));
        danhSachGoc.add(new MonAn("Cơm tấm", "Cơm tấm sườn bì chả, đặc sản Sài Gòn", "620 kcal", R.drawable.comtam));
        danhSachGoc.add(new MonAn("Bánh mì", "Bánh mì giòn nhân thịt nguội, pate thơm ngon", "380 kcal", R.drawable.banhmi));
        danhSachGoc.add(new MonAn("Bún bò Huế", "Bún bò cay nồng, đặc trưng miền Trung", "520 kcal", R.drawable.bunbo));
        danhSachGoc.add(new MonAn("Gỏi cuốn", "Cuốn tươi tôm thịt rau sống, chấm tương đậu", "180 kcal", R.drawable.goicuon));
        danhSachGoc.add(new MonAn("Cháo gà", "Cháo trắng nấu gà xé thơm ngon, dễ tiêu", "280 kcal", R.drawable.chaoga));
        danhSachGoc.add(new MonAn("Mì Quảng", "Mì sợi vàng chan nước nhân tôm thịt đậm vị", "490 kcal", R.drawable.miquang));
        danhSachGoc.add(new MonAn("Canh chua cá", "Canh chua thanh mát nấu cá lóc với me", "210 kcal", R.drawable.canhchua));

        danhSachHien.addAll(danhSachGoc);
        updateCount();
    }

    private void setupListView() {
        adapter = new MonAnAdapter(this, danhSachHien);
        lvMonAn.setAdapter(adapter);

        lvMonAn.setOnItemClickListener((parent, view, position, id) -> {
            MonAn monAn = danhSachHien.get(position);

            // Chuyển sang chi tiết món ăn
            Intent intent = new Intent(this, ChiTietMonAnActivity.class);
            intent.putExtra("ten_mon", monAn.getTenMon());
            intent.putExtra("mo_ta", monAn.getMoTa());
            intent.putExtra("calo", monAn.getCalo());
            intent.putExtra("anh", monAn.getAnh());
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        lvMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MonAnActivity.this, "📌 " + danhSachHien.get(i).getTenMon(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
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
        List<MonAn> ketQua = new ArrayList<>();
        String q = query.toLowerCase().trim();

        if (q.isEmpty()) {
            ketQua.addAll(danhSachGoc);
        } else {
            for (MonAn mon : danhSachGoc) {
                if (mon.getTenMon().toLowerCase().contains(q)
                        || mon.getMoTa().toLowerCase().contains(q)) {
                    ketQua.add(mon);
                }
            }
        }

        danhSachHien.clear();
        danhSachHien.addAll(ketQua);
        adapter.updateList(danhSachHien);
        updateCount();
    }

    private void updateCount() {
        tvCount.setText(danhSachHien.size() + " món ăn");
    }
}