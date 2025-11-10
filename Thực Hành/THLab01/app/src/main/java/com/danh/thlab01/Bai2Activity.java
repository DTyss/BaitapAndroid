package com.danh.thlab01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Bai2Activity extends AppCompatActivity {

    private TextView txtNumber;
    private ImageView imgDice;
    private Button btnRoll;

    private final int[] diceImages = {
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
    };

    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        txtNumber = findViewById(R.id.txtNumber);
        imgDice   = findViewById(R.id.imgDice);
        btnRoll   = findViewById(R.id.btnRoll);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // random từ 1 đến 6
                int value = random.nextInt(6) + 1;
                showResult(value);
            }
        });
    }

    private void showResult(int value) {
        txtNumber.setText("Xúc xắc số: " + value);

        // Giá trị trong đoạn [1..6] -> Con trỏ trong Mảng [0..5]
        imgDice.setImageResource(diceImages[value - 1]);
    }
}
