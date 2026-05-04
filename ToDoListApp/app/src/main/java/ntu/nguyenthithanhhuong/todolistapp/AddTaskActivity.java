package ntu.nguyenthithanhhuong.todolistapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAdd = findViewById(R.id.btnAddTask);
        Button btnCancel = findViewById(R.id.btnCancelTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lay du lieu
                EditText etTaskName = findViewById(R.id.etTaskName);
                EditText etTaskDate = findViewById(R.id.etTaskDate);
                EditText etTaskMessage = findViewById(R.id.etTaskMessage);
                EditText etTaskPriority = findViewById(R.id.etTaskPriority);

                String tenCV = etTaskName.getText().toString();
                String ngayGio = etTaskDate.getText().toString();
                String noiDung = etTaskMessage.getText().toString();
                String doUuTien = etTaskPriority.getText().toString();

                // goi vao TASKS
                TASKS task = new TASKS(tenCV, ngayGio, noiDung, doUuTien);

                // kest noi Firebase va them
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("TASK");
                String key = reference.push().getKey();

                HashMap<String, Object> item = new HashMap<>();
                item.put(key, task.toFirebaseObject());

                reference.updateChildren(item, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            finish();
                        }
                    }
                });
            }
        });
    }
}