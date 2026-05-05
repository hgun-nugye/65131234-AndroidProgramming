package ntu.nguyenthithanhhuong.usefirestore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddFoodActivity extends AppCompatActivity {

    private TextInputEditText edtName, edtPrice, edtDesc;
    private ImageView imgSelected;
    private MaterialButton btnSave;
    private MaterialCardView cardPickImage;

    private FirebaseFirestore db;
    private String base64Image = ""; // Chuỗi mã hóa của ảnh
    private static final int PICK_IMAGE_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_food);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addFood), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        db = FirebaseFirestore.getInstance();
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtDesc = findViewById(R.id.edtDesc);
        imgSelected = findViewById(R.id.imgSelected);
        btnSave = findViewById(R.id.btnSave);
        cardPickImage = findViewById(R.id.cardPickImage);

        cardPickImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        btnSave.setOnClickListener(v -> saveFoodToFirestore());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imgSelected.setImageURI(imageUri);

            // Chuyển ảnh vừa chọn thành Base64 để lưu vào Firestore
            base64Image = imageUriToBase64(imageUri);
        }
    }

    private String imageUriToBase64(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            // Nén ảnh
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void saveFoodToFirestore() {
        String ten = edtName.getText().toString().trim();
        String giaStr = edtPrice.getText().toString().trim();
        String moTa = edtDesc.getText().toString().trim();

        if (ten.isEmpty() || giaStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và giá món!", Toast.LENGTH_SHORT).show();
            return;
        }

        btnSave.setEnabled(false);
        btnSave.setText("Đang lưu...");

        MonAn mon = new MonAn();
        mon.tenMon = ten;
        mon.gia = Long.parseLong(giaStr);
        mon.moTa = moTa;
        mon.hinhAnh = base64Image; // Lưu chuỗi Base64 trực tiếp vào Firestore

        db.collection("menu")
                .add(mon)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Thêm món thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    btnSave.setEnabled(true);
                    btnSave.setText("LƯU MÓN ĂN");
                    Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}