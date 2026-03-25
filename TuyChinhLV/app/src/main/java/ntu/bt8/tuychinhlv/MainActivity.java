package ntu.bt8.tuychinhlv;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        //tim ListView
        ListView lvDSMonAn = (ListView) findViewById(R.id.lvDSMonAn);

        //chuan bi nguon du lieu
        ArrayList<MonAn> dsMonAn = new ArrayList<MonAn>();

        MonAn banhCan = new MonAn(
                "Bánh căn",
                25000,
                "Bánh nhỏ, nướng trên khuôn đất, ăn kèm nước chấm và rau sống.",
                R.drawable.banhcan
        );

        MonAn banhXeo = new MonAn(
                "Bánh xèo",
                30000,
                "Bánh mặn chiên giòn, nhân tôm thịt, ăn kèm rau và nước mắm.",
                R.drawable.banhxeo
        );

        MonAn bunCha = new MonAn(
                "Bún chả",
                40000,
                "Bún ăn với thịt nướng và nước chấm chua ngọt.",
                R.drawable.buncha
        );

        MonAn bun = new MonAn(
                "Bún",
                20000,
                "Sợi bún trắng mềm, thường dùng kèm nhiều món khác.",
                R.drawable.bun
        );

        MonAn nem = new MonAn(
                "Nem",
                35000,
                "Nem rán vàng giòn, nhân thịt và miến, ăn kèm rau sống.",
                R.drawable.nem
        );

        dsMonAn.add(banhCan);
        dsMonAn.add(banhXeo);
        dsMonAn.add(bun);
        dsMonAn.add(bunCha);
        dsMonAn.add(nem);

        MonAnAdapter monAnAdapter = new MonAnAdapter(this, dsMonAn);
        lvDSMonAn.setAdapter(monAnAdapter);

        //bat xu ly su kien
        lvDSMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //lay pham tu duoc cham
                MonAn monAnDuocChon = dsMonAn.get(i);
                Toast.makeText(MainActivity.this, monAnDuocChon.getTenMonAn(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}