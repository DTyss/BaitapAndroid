package com.danh.thlab07;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Baitap3Activity extends AppCompatActivity {

    private SeekBar sbRed, sbGreen, sbBlue;
    private View viewColor, viewC, viewM, viewY;
    private TextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap3);

        sbRed = findViewById(R.id.sbRed);
        sbGreen = findViewById(R.id.sbGreen);
        sbBlue = findViewById(R.id.sbBlue);
        viewColor = findViewById(R.id.viewColor);
        viewC = findViewById(R.id.viewC);
        viewM = findViewById(R.id.viewM);
        viewY = findViewById(R.id.viewY);
        tvValue = findViewById(R.id.tvValue);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor();
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        sbRed.setOnSeekBarChangeListener(listener);
        sbGreen.setOnSeekBarChangeListener(listener);
        sbBlue.setOnSeekBarChangeListener(listener);

        updateColor();
    }

    private void updateColor() {
        int r = sbRed.getProgress();
        int g = sbGreen.getProgress();
        int b = sbBlue.getProgress();

        int color = Color.rgb(r, g, b);
        viewColor.setBackgroundColor(color);

        int c = 255 - r;
        int m = 255 - g;
        int y = 255 - b;

        viewC.setBackgroundColor(Color.rgb(0, m, y));
        viewM.setBackgroundColor(Color.rgb(r, 0, y));
        viewY.setBackgroundColor(Color.rgb(r, g, 0));

        tvValue.setText(String.format("R: %d | G: %d | B: %d", r, g, b));
    }
}