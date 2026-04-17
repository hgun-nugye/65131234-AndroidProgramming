package ntu.ntth.ontapgk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class ActivityBMI extends AppCompatActivity {

    TextInputEditText etWeight, etHeight;
    MaterialButton btnCalculate, btnBack;
    TextView tvBmiValue, tvBmiStatus;
    View llResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        etWeight = findViewById(R.id.et_weight);
        etHeight = findViewById(R.id.et_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnBack = findViewById(R.id.btn_back);
        tvBmiValue = findViewById(R.id.tv_bmi_value);
        tvBmiStatus = findViewById(R.id.tv_bmi_status);
        llResult = findViewById(R.id.ll_result);

        // Hide result initially
        llResult.setVisibility(View.GONE);

        btnCalculate.setOnClickListener(v -> calculateBMI());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

    private void calculateBMI() {
        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            if (height <= 0) {
                Toast.makeText(this, "Chiều cao phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmi = weight / (height * height);
            displayResult(bmi);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayResult(float bmi) {
        llResult.setVisibility(View.VISIBLE);
        tvBmiValue.setText(String.format(Locale.getDefault(), "%.1f", bmi));

        String status;
        int color;

        if (bmi < 18.5) {
            status = "Gầy";
            color = ContextCompat.getColor(this, R.color.bmi_low);
        } else if (bmi < 24.9) {
            status = "Bình thường";
            color = ContextCompat.getColor(this, R.color.bmi_normal);
        } else if (bmi < 29.9) {
            status = "Tiền béo phì";
            color = ContextCompat.getColor(this, R.color.bmi_high);
        } else if (bmi < 34.9) {
            status = "Béo phì độ I";
            color = ContextCompat.getColor(this, R.color.bmi_obese);
        } else if (bmi < 39.9) {
            status = "Béo phì độ II";
            color = ContextCompat.getColor(this, R.color.bmi_obese);
        } else {
            status = "Béo phì cực độ (độ III)";
            color = ContextCompat.getColor(this, R.color.bmi_obese);
        }

        tvBmiStatus.setText(status);
        tvBmiStatus.setTextColor(color);
        tvBmiValue.setTextColor(color);
    }
}