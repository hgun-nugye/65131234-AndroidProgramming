package ntu.bth7.listview1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MonAnActivity extends AppCompatActivity {
    ListView listView;
    Button btnChuyen;
    String[] monAn = {"Bún đậu mắm tôm", "Bún cá Ninh Hòa", "Bánh xèo", "Cháo lòng bánh hỏi"};

    void getDieuKhien(){
        listView = findViewById(R.id.listView);
        btnChuyen = findViewById(R.id.btnChuyen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);

        getDieuKhien();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, monAn);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MonAnActivity.this, "Bạn đã chọn món ăn: "+ monAn[i], Toast.LENGTH_SHORT).show();
            }
        });

        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonAnActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}