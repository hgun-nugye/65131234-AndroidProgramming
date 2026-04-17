package ntu.ntth.ontapgk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChiTietMonAn extends AppCompatActivity {
    TextView tvTen;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_mon_an);

        String ten = getIntent().getStringExtra("ten");
        tvTen = findViewById(R.id.tv_ten);
        tvTen.setText(ten);
        btnBack = findViewById(R.id.btn_back);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietMonAn.this, ActivityMonAn.class);
                startActivity(intent);
            }
        });
    }
}