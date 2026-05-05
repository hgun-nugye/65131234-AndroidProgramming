package ntu.nguyenthithanhhuong.usefirestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.graphics.shapes.Feature;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvFood;
    ExtendedFloatingActionButton btnAdd;

    List<MonAn> list;
    FoodAdapter adapter;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvFood = findViewById(R.id.rvFood);
        btnAdd = findViewById(R.id.btnAdd);

        db = FirebaseFirestore.getInstance();

        list = new ArrayList<>();
        adapter = new FoodAdapter(list);

        rvFood.setLayoutManager(new LinearLayoutManager(this));
        rvFood.setAdapter(adapter);

        addData();

        loadData();

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddFoodActivity.class));
        });
    }

    private void loadData() {
        db.collection("menu")
                .get()
                .addOnSuccessListener(query -> {
                    list.clear();
                    for (DocumentSnapshot doc : query) {
                        MonAn mon = doc.toObject(MonAn.class);
                        list.add(mon);
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void addData() {
        // Danh sách món ăn mẫu
        List<MonAn> sampleList = new ArrayList<>();

        // Món 1: Phở Bò
        MonAn m1 = new MonAn();
        m1.tenMon = "Phở Bò Gia Truyền";
        m1.gia = 45000L;
        m1.moTa = "Phở bò tái nạm, nước dùng thanh ngọt xương ống.";
        m1.hinhAnh = ""; // Để trống hoặc dán Base64 nếu có
        sampleList.add(m1);

        // Món 2: Bún Chả
        MonAn m2 = new MonAn();
        m2.tenMon = "Bún Chả Hà Nội";
        m2.gia = 35000L;
        m2.moTa = "Chả nướng than hoa cơm cháy, ăn kèm nước mắm chua ngọt.";
        m2.hinhAnh = "";
        sampleList.add(m2);

        // Món 3: Cà phê sữa đá
        MonAn m3 = new MonAn();
        m3.tenMon = "Cà Phê Sữa Đá";
        m3.gia = 20000L;
        m3.moTa = "Cà phê Robusta đậm đặc pha máy.";
        m3.hinhAnh = "";
        sampleList.add(m3);

        // Đẩy từng món lên Firestore
        for (MonAn mon : sampleList) {
            db.collection("menu")
                    .add(mon)
                    .addOnSuccessListener(documentReference -> {
                        // Thành công
                    });
        }

        Toast.makeText(this, "Đã thêm dữ liệu mẫu!", Toast.LENGTH_SHORT).show();
    }
}