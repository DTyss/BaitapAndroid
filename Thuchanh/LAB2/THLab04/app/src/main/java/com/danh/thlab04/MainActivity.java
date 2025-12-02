package com.danh.thlab04;

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
        Button btnBai3 = findViewById(R.id.btn_bai3);

        btnBai1.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Baitap1Activity.class));
        });

        btnBai2.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Baitap2Activity.class));
        });

        btnBai3.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Baitap3Activity.class));
        });
    }
}