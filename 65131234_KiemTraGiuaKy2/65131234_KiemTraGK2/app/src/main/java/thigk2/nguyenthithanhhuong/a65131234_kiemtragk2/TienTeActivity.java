package thigk2.nguyenthithanhhuong.a65131234_kiemtragk2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class TienTeActivity extends AppCompatActivity {
    EditText et_VND, et_tigia;
    TextView tv_USD;
    Button btn_calculate, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tien_te);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tiente), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getDieuKhien();
    }

    public void getDieuKhien(){
        et_VND = findViewById(R.id.et_VND);
        tv_USD = findViewById(R.id.tv_USD);
        btn_calculate = findViewById(R.id.btn_calculate);
        et_tigia = findViewById(R.id.et_tigia);
        btnBack = findViewById(R.id.btn_back);
    }
}