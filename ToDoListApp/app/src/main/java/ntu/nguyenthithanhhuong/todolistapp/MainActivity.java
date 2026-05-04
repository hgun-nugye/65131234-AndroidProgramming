package ntu.nguyenthithanhhuong.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<TASKS> tasks;
    TaskAdapter adapter;

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
        FloatingActionButton add = findViewById(R.id.btnAddTask);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        // Khởi tạo list
        tasks = new ArrayList<>();

        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("TASK");

        // RecyclerView
        RecyclerView rcvTask = findViewById(R.id.recyclerTasks);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        rcvTask.setLayoutManager(layout);

        rcvTask.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

        adapter = new TaskAdapter(tasks);
        rcvTask.setAdapter(adapter);

        // Lắng nghe Firebase
        reference.addValueEventListener(ngheFB);
    }

    ValueEventListener ngheFB = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            tasks.clear();

            for (DataSnapshot obj : snapshot.getChildren()) {

                TASKS task = obj.getValue(TASKS.class);

                if (task != null) {
                    tasks.add(task);

                    Log.d("TODO_APP",
                            "Việc cần làm: " + task.getName());
                }
            }

            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

            Toast.makeText(MainActivity.this,
                    "Lỗi Firebase",
                    Toast.LENGTH_SHORT).show();
        }
    };
}