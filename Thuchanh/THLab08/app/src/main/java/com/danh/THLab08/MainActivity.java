package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnList, btnGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = findViewById(R.id.btnList);
        btnGrid = findViewById(R.id.btnGrid);

        btnList.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ListActivity.class)));

        btnGrid.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, GridActivity.class)));
    }
}
