package ntu.ntth.ontapgk;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ActivityThem extends AppCompatActivity {

    Button[] buttons = new Button[9];
    boolean playerX = true; // người = X, máy = O
    TextView tvStatus;

    MediaPlayer clickSound, winSound;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        tvStatus = findViewById(R.id.tv_status);

        // âm thanh
//        clickSound = MediaPlayer.create(this, R.raw.click);
//        winSound = MediaPlayer.create(this, R.raw.win);

        // ánh xạ nút
        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("btn" + i, "id", getPackageName());
            buttons[i] = findViewById(id);

            int finalI = i;
            buttons[i].setOnClickListener(v -> handlePlayerMove(finalI));
        }

        findViewById(R.id.btn_reset).setOnClickListener(v -> resetGame());
    }

    // người chơi
    void handlePlayerMove(int i) {
        if (!buttons[i].getText().toString().equals("")) return;

//        clickSound.start();
        buttons[i].setText("X");

        if (checkWin()) {
//            winSound.start();
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
        int i;

        do {
            i = random.nextInt(9);
        } while (!buttons[i].getText().toString().equals(""));

//        clickSound.start();
        buttons[i].setText("O");

        if (checkWin()) {
//            winSound.start();
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
        if (clickSound != null) clickSound.release();
        if (winSound != null) winSound.release();
    }
}