package com.danh.thlab07;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnBt1).setOnClickListener(v ->
                startActivity(new Intent(this, Baitap1Activity.class)));

        findViewById(R.id.btnBt2).setOnClickListener(v ->
                startActivity(new Intent(this, Baitap2Activity.class)));

        findViewById(R.id.btnBt3).setOnClickListener(v ->
                startActivity(new Intent(this, Baitap3Activity.class)));
    }
}