package ntu.bt8.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LanscapeAdapter lanscapeAdapter;
    ArrayList<Landscape> recyclerViewData;
    RecyclerView recyclerViewLandscape;

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

        recyclerViewData = getDataForRecyclerView();
        recyclerViewLandscape = findViewById(R.id.recyclerLand);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLandscape.setLayoutManager(layoutManager);

        //layout truot ngang
//        RecyclerView.LayoutManager layoutHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewLandscape.setLayoutManager(layoutHorizontal);

        //layout grid
//        RecyclerView.LayoutManager layoutGrid = new GridLayoutManager(this, 2);
//        recyclerViewLandscape.setLayoutManager(layoutGrid);

        lanscapeAdapter = new LanscapeAdapter(this, recyclerViewData);
        recyclerViewLandscape.setAdapter(lanscapeAdapter);
    }

    //phuong thuc
    ArrayList<Landscape> getDataForRecyclerView() {
        ArrayList<Landscape> lstData = new ArrayList<>();
        lstData.add(new Landscape("nuidoi", "Núi đôi hùng vĩ"));
        lstData.add(new Landscape("thomong", "Khung cảnh thơ mộng"));
        lstData.add(new Landscape("troihong", "Bầu trời hồng rực rỡ"));
        lstData.add(new Landscape("troixanh", "Trời xanh bao la"));
        return lstData;
    }
}