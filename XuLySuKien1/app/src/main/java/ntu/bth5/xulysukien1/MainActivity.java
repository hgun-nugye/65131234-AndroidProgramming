package ntu.bth5.xulysukien1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText inputA, inputB;
    TextView kq;
    Button btnTiepTuc, btnThoat;
    Button btnCong, btnTru, btnNhan, btnChia;

    void getDieuKhien() {
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        kq = findViewById(R.id.kq);

        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        btnThoat = findViewById(R.id.btnThoat);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDieuKhien();

        View.OnClickListener boLangNgheCong = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cong();
            }
        };

        btnCong.setOnClickListener(boLangNgheCong);

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tru();
            }
        });

        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nhan();
            }
        });

        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chia();
            }
        });

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTiepTuc();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnThoat();
            }
        });
    }

    public void btnTiepTuc() {
        inputA.setText("");
        inputB.setText("");
        kq.setText("");
    }

    public void Cong() {
        String sa = inputA.getText().toString().trim();
        String sb = inputB.getText().toString().trim();

        if (sa.isEmpty() || sb.isEmpty()) {
            Toast.makeText(MainActivity.this, "Vui lòng nhập đủ 2 số!", Toast.LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(inputA.getText().toString());
        double b = Double.parseDouble(inputB.getText().toString());

        double tong = a + b;
        kq.setText(String.valueOf(tong));

    }

    public void Tru() {
        String sa = inputA.getText().toString().trim();
        String sb = inputB.getText().toString().trim();

        if (sa.isEmpty() || sb.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ 2 số!", Toast.LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(inputA.getText().toString());
        double b = Double.parseDouble(inputB.getText().toString());

        double hieu = a - b;
        kq.setText(String.valueOf(hieu));

    }

    public void Nhan() {
        String sa = inputA.getText().toString().trim();
        String sb = inputB.getText().toString().trim();

        if (sa.isEmpty() || sb.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ 2 số!", Toast.LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(inputA.getText().toString());
        double b = Double.parseDouble(inputB.getText().toString());

        double tich = a * b;
        kq.setText(String.valueOf(tich));

    }

    public void Chia() {
        String sa = inputA.getText().toString().trim();
        String sb = inputB.getText().toString().trim();

        if (sa.isEmpty() || sb.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ 2 số!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sb.equals("0")) {
            Toast.makeText(this, "Không thể chia cho 0!", Toast.LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(inputA.getText().toString());
        double b = Double.parseDouble(inputB.getText().toString());

        double thuong = a / b;
        kq.setText(String.valueOf(thuong));

    }

    public void btnThoat() {
        finish();
    }

}