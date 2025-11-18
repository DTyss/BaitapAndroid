package com.danh.thlab03;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class Baitap1Activity extends AppCompatActivity {

    private Switch swRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap1);

        initViews();
    }

    private void initViews() {
        swRotate = findViewById(R.id.sw_rotate);

        swRotate.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }
}