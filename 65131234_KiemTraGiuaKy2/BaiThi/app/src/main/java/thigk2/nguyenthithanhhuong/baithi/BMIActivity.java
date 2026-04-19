package thigk2.nguyenthithanhhuong.baithi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class BMIActivity extends AppCompatActivity {
    private TextInputEditText etName, etWeight, etHeight;
    private MaterialButton btnCalculate, btnReset;
    private MaterialCardView cardInput, cardResult;
    private TextView tvResultName, tvBmiValue, tvBmiCategory, tvBmiDesc;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bmi), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getDieuKhien();
        setupListeners();
        btnBack.setOnClickListener(v -> finish());

    }

    private void getDieuKhien() {
        etName = findViewById(R.id.et_name);
        etWeight = findViewById(R.id.et_weight);
        etHeight = findViewById(R.id.et_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnReset = findViewById(R.id.btn_reset);
        cardInput = findViewById(R.id.card_input);
        cardResult = findViewById(R.id.card_result);
        tvResultName = findViewById(R.id.tv_result_name);
        tvBmiValue = findViewById(R.id.tv_bmi_value);
        tvBmiCategory = findViewById(R.id.tv_bmi_category);
        tvBmiDesc = findViewById(R.id.tv_bmi_desc);
        btnBack = findViewById(R.id.btn_back);

    }

    private void setupListeners() {
        btnCalculate.setOnClickListener(v -> tinhBMI());
        btnReset.setOnClickListener(v -> resetForm());
        btnBack.setOnClickListener(v->finish());
    }

    private void tinhBMI() {
        String name = etName.getText() != null ? etName.getText().toString().trim() : "";
        String weight = etWeight.getText() != null ? etWeight.getText().toString().trim() : "";
        String height = etHeight.getText() != null ? etHeight.getText().toString().trim() : "";

        if (TextUtils.isEmpty(weight) || TextUtils.isEmpty(height)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ cân nặng và chiều cao!", Toast.LENGTH_SHORT).show();
            return;
        }

        double w = Double.parseDouble(weight);
        double h = Double.parseDouble(height) / 100.0; // cm → m

        if (w <= 0 || h <= 0) {
            Toast.makeText(this, "Giá trị không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        double bmi = w / (h * h);
        String bmiStr = String.format("%.1f", bmi);

        // Tên hiển thị
        String displayName = TextUtils.isEmpty(name) ? "Bạn" : name;
        tvResultName.setText("Xin chào, " + displayName + "!");

        // Hiển thị chỉ số
        tvBmiValue.setText(bmiStr);

        // Phân loại
        BMIResult result = phanLoaiBMI(bmi);
        tvBmiCategory.setText(result.emoji + " " + result.category);
        tvBmiCategory.setTextColor(getColor(result.colorRes));
        tvBmiDesc.setText(result.description);

        // Hiệu ứng chuyển card
        cardInput.animate().alpha(0f).translationY(-20f).setDuration(250).withEndAction(() -> {
            cardInput.setVisibility(View.GONE);
            cardResult.setVisibility(View.VISIBLE);
            cardResult.setAlpha(0f);
            cardResult.setTranslationY(30f);
            cardResult.animate().alpha(1f).translationY(0f).setDuration(350).start();
        }).start();
    }

    // Phân loại bmi
    private BMIResult phanLoaiBMI(double bmi) {
        if (bmi < 18.5) {
            return new BMIResult(
                    "Thiếu cân", "⚠️",
                    android.R.color.holo_blue_dark,
                    "Chỉ số BMI của bạn thấp hơn mức bình thường. Hãy tăng cường dinh dưỡng và tham khảo ý kiến bác sĩ."
            );
        } else if (bmi < 23.0) {
            return new BMIResult(
                    "Bình thường", "✅",
                    android.R.color.holo_green_dark,
                    "Tuyệt vời! Chỉ số BMI của bạn hoàn toàn bình thường. Hãy duy trì lối sống lành mạnh mỗi ngày!"
            );
        } else if (bmi < 25.0) {
            return new BMIResult(
                    "Thừa cân", "⚡",
                    android.R.color.holo_orange_dark,
                    "Bạn đang ở mức thừa cân. Nên điều chỉnh chế độ ăn và tăng cường vận động thể dục thể thao."
            );
        } else {
            return new BMIResult(
                    "Béo phì", "🔴",
                    android.R.color.holo_red_dark,
                    "Chỉ số BMI ở mức béo phì. Bạn nên tham khảo bác sĩ để có kế hoạch giảm cân an toàn và hiệu quả."
            );
        }
    }

    private void resetForm() {
        etName.setText("");
        etWeight.setText("");
        etHeight.setText("");

        cardResult.animate().alpha(0f).translationY(20f).setDuration(250).withEndAction(() -> {
            cardResult.setVisibility(View.GONE);
            cardInput.setVisibility(View.VISIBLE);
            cardInput.setAlpha(0f);
            cardInput.setTranslationY(-20f);
            cardInput.animate().alpha(1f).translationY(0f).setDuration(350).start();
        }).start();
    }

   // Gắn kết quả
    static class BMIResult {
        String category, emoji, description;
        int colorRes;

        BMIResult(String category, String emoji, int colorRes, String description) {
            this.category = category;
            this.emoji = emoji;
            this.colorRes = colorRes;
            this.description = description;
        }
    }
}