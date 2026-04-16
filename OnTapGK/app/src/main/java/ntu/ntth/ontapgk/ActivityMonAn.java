package ntu.ntth.ontapgk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ActivityMonAn extends AppCompatActivity {
    ListView lvMonAn;
    MaterialButton btnBack;
    ArrayList<String> dsMonAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mon_an);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvMonAn = findViewById(R.id.lv_monan);
        btnBack = findViewById(R.id.btn_back);

        dsMonAn = new ArrayList<>();
        dsMonAn.add("Phở Bò Hà Nội");
        dsMonAn.add("Bún Chả");
        dsMonAn.add("Bánh Mì Sài Gòn");
        dsMonAn.add("Cơm Tấm");
        dsMonAn.add("Gỏi Cuốn");
        dsMonAn.add("Bún Bò Huế");
        dsMonAn.add("Mì Quảng");
        dsMonAn.add("Bánh Xèo");
        dsMonAn.add("Chả Cá Lã Vọng");
        dsMonAn.add("Cao Lầu");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsMonAn);
        lvMonAn.setAdapter(adapter);

        lvMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = dsMonAn.get(i);
                Toast.makeText(ActivityMonAn.this, "Bạn chọn: " + selected, Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMonAn.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}