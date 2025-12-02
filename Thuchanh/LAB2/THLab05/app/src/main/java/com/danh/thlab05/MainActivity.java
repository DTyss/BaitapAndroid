package com.danh.thlab05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBai1 = findViewById(R.id.btn_bai1);
        Button btnBai2 = findViewById(R.id.btn_bai2);

        btnBai1.setOnClickListener(v -> {
            v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(() -> {
                startActivity(new Intent(MainActivity.this, Baitap1Activity.class));
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
            }).start();
        });

        btnBai2.setOnClickListener(v -> {
            v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(() -> {
                startActivity(new Intent(MainActivity.this, Baitap2Activity.class));
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
            }).start();
        });
    }
}