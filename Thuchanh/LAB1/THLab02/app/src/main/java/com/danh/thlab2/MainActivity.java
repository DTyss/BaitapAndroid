package com.danh.thlab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSplash = findViewById(R.id.btn_splash);
        Button btnProfile = findViewById(R.id.btn_profile);

        // Nút chuyển sang Splash Screen
        btnSplash.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, M001ActSplash.class);
            startActivity(intent);
        });

        // Nút chuyển sang Profile Screen
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, M001ActProfile.class);
            startActivity(intent);
        });
    }
}
