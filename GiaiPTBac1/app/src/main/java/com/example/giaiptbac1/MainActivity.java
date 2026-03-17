package com.example.giaiptbac1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText inputA, inputB;
    TextView kq;
    Button btnTiepTuc, btnGiaiPT, btnThoat;

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
        addViews();
    }

    private void addViews() {
        inputA = (EditText) findViewById(R.id.inputA);
        inputB = (EditText) findViewById(R.id.inputB);
        btnTiepTuc =(Button) findViewById(R.id.btnTiepTuc);
        btnGiaiPT =(Button) findViewById(R.id.btnGiaiPT);
        btnThoat =(Button) findViewById(R.id.btnThoat);
        kq = (TextView) findViewById(R.id.kq);
    }

    public void xulyTiepTuc(View view) {
        inputA.setText("");
        inputB.setText("");
        kq.setText("");
    }

    public void xulyGiaiPT(View view) {
        if (inputA.getText().toString().equals("") || inputB.getText().toString().equals("")) {
//            Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ a và b", Toast.LENGTH_SHORT).show();
            inputA.setError("Vui lòng nhập a");
            inputB.setError("Vui lòng nhập b");
            return;
        }
        double a = Double.parseDouble(inputA.getText().toString());
        double b = Double.parseDouble(inputB.getText().toString());

        if(a == 0 && b == 0){
            kq.setText("Phương trình vô số nghiệm");
            return;
        }
        if(a == 0 && b != 0){
            kq.setText("Phương trình vô nghiệm");
            return;
        }
        if(a != 0 && b == 0){
            kq.setText("0");
            return;
        }
        if(a != 0 && b != 0) {
            double x = -b / a;
            kq.setText(String.valueOf(x));
        }
    }

    public void xulyThoat(View view) {
        finish();
    }
}