package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity3 extends AppCompatActivity {

    private Button btnQuickJob, btnSlowJob;
    private TextView tvStatus;
    private SlowTask slowTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        findViewByIds();
        initVariables();
    }

    private void findViewByIds() {
        btnQuickJob = findViewById(R.id.btn_quick_job);
        btnSlowJob = findViewById(R.id.btn_slow_job);
        tvStatus = findViewById(R.id.tv_status);
    }

    private void initVariables() {
        slowTask = new SlowTask(Activity3.this, tvStatus);

        // Xử lý Quick Job - hiển thị thời gian hiện tại ngay lập tức
        btnQuickJob.setOnClickListener(v -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            tvStatus.setText(sdf.format(new Date()));
        });

        // Xử lý Slow Job - chạy AsyncTask
        btnSlowJob.setOnClickListener(v -> {
            // Kiểm tra nếu task chưa chạy hoặc đã hoàn thành thì mới chạy
            if (slowTask.getStatus() == AsyncTask.Status.PENDING) {
                slowTask.execute();
            }
        });
    }
}