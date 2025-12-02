package com.danh.thlab03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnBaitap1, btnBaitap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBaitap1 = findViewById(R.id.btn_baitap1);
        btnBaitap2 = findViewById(R.id.btn_baitap2);

        btnBaitap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Baitap1Activity.class));
            }
        });

        btnBaitap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Baitap2Activity.class));
            }
        });
    }
}