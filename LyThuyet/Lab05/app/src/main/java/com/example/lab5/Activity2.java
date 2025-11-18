package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private ProgressBar pbWaiting;
    private TextView tvTopCaption;
    private EditText etInput;
    private Button btnExecute;
    private int globalValue = 0;
    private int accum = 0;
    private long startTime;
    private final String PATIENCE = "Some important data is being collected now.\nPlease be patient...wait...";
    private Handler handler;
    private Runnable fgRunnable, bgRunnable;
    private Thread testThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        findViewByIds();
        initVariables();
    }

    private void findViewByIds() {
        tvTopCaption = findViewById(R.id.tv_top_caption);
        pbWaiting = findViewById(R.id.pb_waiting);
        etInput = findViewById(R.id.et_input);
        btnExecute = findViewById(R.id.btn_execute);
    }

    private void initVariables() {
        globalValue = 0;
        accum = 0;
        startTime = System.currentTimeMillis();
        handler = new Handler();

        // Định nghĩa bgRunnable - chạy nền, tăng globalValue mỗi 1 giây
        bgRunnable = new Runnable() {
            @Override
            public void run() {
                if (testThread != null && testThread.isAlive()) {
                    synchronized (Activity2.this) {
                        globalValue += 1;
                    }
                    tvTopCaption.setText("Global Value: " + globalValue);
                    handler.postDelayed(bgRunnable, 1000); // Chạy lại sau 1 giây
                }
            }
        };

        // Định nghĩa fgRunnable - chạy trước, tăng globalValue 100 mỗi lần
        fgRunnable = new Runnable() {
            @Override
            public void run() {
                if (testThread != null && testThread.isAlive()) {
                    synchronized (Activity2.this) {
                        globalValue += 100;
                        accum++;
                    }
                    pbWaiting.setProgress(accum);
                    tvTopCaption.setText("Global Value: " + globalValue);
                    handler.postDelayed(fgRunnable, 500); // Chạy lại sau 0.5 giây
                } else {
                    // Thread kết thúc
                    tvTopCaption.setText(getString(R.string.bg_work_is_over));
                    Toast.makeText(Activity2.this, "Final Global Value: " + globalValue,
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Xử lý sự kiện Execute button
        btnExecute.setOnClickListener(v -> {
            String input = etInput.getText().toString();
            if (!input.isEmpty()) {
                Toast.makeText(Activity2.this, "Input: " + input, Toast.LENGTH_SHORT).show();
            }
        });

        // Khởi động background thread
        tvTopCaption.setText(PATIENCE);
        pbWaiting.setMax(10);
        pbWaiting.setProgress(0);

        testThread = new Thread(() -> {
            try {
                Thread.sleep(5000); // Chạy background task trong 5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        testThread.start();

        // Bắt đầu postDelayed cho cả hai Runnable
        handler.post(bgRunnable);
        handler.post(fgRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy các Runnable khi Activity bị destroy
        handler.removeCallbacks(bgRunnable);
        handler.removeCallbacks(fgRunnable);
    }
}