package thigk2.nguyenthithanhhuong.baithi;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class LamThemActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    boolean playerX = true; // người = X, máy = O
    TextView tvStatus;

    ImageButton btnBack;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lam_them);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btn_back);
        tvStatus = findViewById(R.id.tv_status);

        // ánh xạ nút
        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("btn" + i, "id", getPackageName());
            buttons[i] = findViewById(id);

            int finalI = i;
            buttons[i].setOnClickListener(v -> handlePlayerMove(finalI));
        }

        findViewById(R.id.btn_reset).setOnClickListener(v -> resetGame());
        btnBack.setOnClickListener(v -> finish());
    }

    // người chơi
    void handlePlayerMove(int i) {
        if (!buttons[i].getText().toString().equals("")) return;

        buttons[i].setText("X");

        if (checkWin()) {
            tvStatus.setText("Bạn thắng!");
            tvStatus.setTextColor(Color.GREEN);
            disableAll();
            return;
        }

        if (isBoardFull()) {
            tvStatus.setText("Hòa!");
            return;
        }

        tvStatus.setText("Máy đang chơi...");
        buttons[i].postDelayed(this::computerMove, 500); // delay cho tự nhiên
    }

    // máy chơi (random)
    void computerMove() {
        int move = findBestMoveSimple();

        buttons[move].setText("O");

        if (checkWin()) {
            tvStatus.setText("Máy thắng!");
            tvStatus.setTextColor(Color.RED);
            disableAll();
            return;
        }

        if (isBoardFull()) {
            tvStatus.setText("Hòa!");
            return;
        }

        tvStatus.setText("Lượt của bạn");
    }

    boolean checkWin() {
        int[][] winPos = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pos : winPos) {
            String a = buttons[pos[0]].getText().toString();
            String b = buttons[pos[1]].getText().toString();
            String c = buttons[pos[2]].getText().toString();

            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                return true;
            }
        }

        return false;
    }

    boolean isBoardFull() {
        for (Button b : buttons) {
            if (b.getText().toString().equals("")) return false;
        }
        return true;
    }

    void disableAll() {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }

    void resetGame() {
        tvStatus.setText("Lượt của bạn");

        for (Button b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    int findBestMoveSimple() {
        // 1. Nếu máy thắng được
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().toString().equals("")) {
                buttons[i].setText("O");
                if (checkWin()) {
                    buttons[i].setText("");
                    return i;
                }
                buttons[i].setText("");
            }
        }

        // 2. Chặn người chơi
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().toString().equals("")) {
                buttons[i].setText("X");
                if (checkWin()) {
                    buttons[i].setText("");
                    return i;
                }
                buttons[i].setText("");
            }
        }

        // 3. Random
        int i;
        do {
            i = random.nextInt(9);
        } while (!buttons[i].getText().toString().equals(""));

        return i;
    }
}