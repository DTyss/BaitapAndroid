package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private Button btnStart;
    private boolean isRunning;
    private int MAX_SEC = 10;
    private int intTest;
    private Thread bgThread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        initVariables();
    }

    private void findViewByIds() {
        pbFirst = findViewById(R.id.pb_first);
        pbSecond = findViewById(R.id.pb_second);
        tvMsgWorking = findViewById(R.id.tv_working);
        tvMsgReturned = findViewById(R.id.tv_return);
        btnStart = findViewById(R.id.btn_start);
    }

    private void initVariables() {
        isRunning = false;
        intTest = 0;

        pbFirst.setMax(MAX_SEC);
        pbSecond.setMax(MAX_SEC);

        // Tạo Handler để xử lý message từ background thread
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                int randomNum = msg.arg1;
                int count = msg.arg2;

                pbFirst.setProgress(count);
                tvMsgWorking.setText(getString(R.string.working));
                tvMsgReturned.append(getString(R.string.returned_by_bg_thread)
                        + "Random: " + randomNum
                        + getString(R.string.global_value_seen)
                        + ": " + intTest + "\n");

                if (count >= MAX_SEC) {
                    tvMsgWorking.setText(getString(R.string.done_background_thread_has_been_stopped));
                    isRunning = false;
                }
            }
        };

        // Xử lý sự kiện click button Start (Bài 1)
        btnStart.setOnClickListener(v -> {
            if (!isRunning) {
                isRunning = true;
                tvMsgReturned.setText("");
                intTest = 0;
                pbFirst.setProgress(0);
                initBgThread();
            }
        });

        // Xử lý nút chuyển đổi bài tập
        Button btnBai1 = findViewById(R.id.btn_bai1);
        Button btnBai2 = findViewById(R.id.btn_bai2);
        Button btnBai3 = findViewById(R.id.btn_bai3);
        Button btnBai4 = findViewById(R.id.btn_bai4);

        btnBai1.setOnClickListener(v -> {
            // Ở lại bài 1 hoặc reload
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();
        });

        btnBai2.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Activity2.class));
            finish();
        });

        btnBai3.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Activity3.class));
            finish();
        });

        btnBai4.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Activity4.class));
            finish();
        });
    }

    private void initBgThread() {
        bgThread = new Thread(() -> {
            for (int i = 1; i <= MAX_SEC && isRunning; i++) {
                try {
                    // Tạo số ngẫu nhiên từ 0 đến 100
                    int randomNum = (int) (Math.random() * 101);
                    intTest++;

                    // Tạo Message
                    Message msg = handler.obtainMessage();
                    msg.arg1 = randomNum;
                    msg.arg2 = i;

                    // Gửi message
                    handler.sendMessage(msg);

                    // Chờ 1 giây
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        bgThread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }
}