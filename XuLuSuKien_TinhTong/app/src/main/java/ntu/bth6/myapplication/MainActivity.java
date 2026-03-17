package ntu.bth6.myapplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //gắn layout tương ứng với file
        setContentView(R.layout.activity_main);
    }

    public void XuLyCong(View view) {
        //tìm tham chiếu điều khiển xml và mapping sang java
        EditText editTextSoA = findViewById(R.id.edtA);
        EditText editTextSoB = findViewById(R.id.edtB);
        TextView textViewKetQua = findViewById(R.id.tvketQua);

        //lấy dữ liệu về ở điều khiển số a
        String strA = editTextSoA.getText().toString();
        //lấy dữ liệu về ở điều khiển số b
        String strB = editTextSoB.getText().toString();

        //Chuyển dữ liệu sang dạng số a
        double a = Double.parseDouble(strA);
        //Chuyển dữ liệu sang dạng số b
        double b = Double.parseDouble(strB);

        //Tính toán theo yêu cầu
        double ketQua = a + b;

        //Hiện ra màn hình
        String kq = String.valueOf(ketQua);
        textViewKetQua.setText(kq);

    }
}