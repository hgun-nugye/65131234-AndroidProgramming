package ntu.bt.recycle_layout;


import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvProducts;
    SanPhamAdapter adapter;
    List<SanPham> list;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ánh xạ
        rvProducts = findViewById(R.id.rvProducts);
        edtSearch = findViewById(R.id.edtSearch);

        // layout cho recyclerView
        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        // tạo dữ liệu mẫu
        list = new ArrayList<>();
        list.add(new SanPham("Áo thun", "Cotton 100%", "120.000đ", R.drawable.sp));
        list.add(new SanPham("Quần jean", "Slim fit", "250.000đ", R.drawable.sp));
        list.add(new SanPham("Áo khoác", "Chống nắng", "300.000đ", R.drawable.sp));

        // adapter
        adapter = new SanPhamAdapter(this, list);
        rvProducts.setAdapter(adapter);

    }
}