package com.BT2.conghaiso;

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
    Button btnTiepTuc, btnThoat;
    Button btnTong;

    void getDieuKhien() {
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        kq = findViewById(R.id.kq);

        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        btnThoat = findViewById(R.id.btnThoat);

        btnTong = findViewById(R.id.btnTong);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDieuKhien();

    }

    public void btnTiepTuc(View view) {
        inputA.setText("");
        inputB.setText("");
        kq.setText("");
    }

    public void Cong(View view) {
        String sa = inputA.getText().toString().trim();
        String sb = inputB.getText().toString().trim();

        if (sa.isEmpty() || sb.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ 2 số!", Toast.LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(inputA.getText().toString());
        double b = Double.parseDouble(inputB.getText().toString());

        double tong = a + b;
        kq.setText(String.valueOf(tong));

    }

    public void btnThoat(View view) {
        finish();
    }

}