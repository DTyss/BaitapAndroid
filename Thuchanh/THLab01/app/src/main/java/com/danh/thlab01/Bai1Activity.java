package com.danh.thlab01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Bai1Activity extends AppCompatActivity {

    EditText txtX, txtY;
    TextView txtResult;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnModulo;

    private void initControll() {
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtResult = findViewById(R.id.txtResult);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnModulo = findViewById(R.id.btnModulo);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sx = txtX.getText().toString().trim();
                String sy = txtY.getText().toString().trim();

                if (sx.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập X", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sy.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập Y", Toast.LENGTH_SHORT).show();
                    return;
                }

                int x = Integer.parseInt(sx);
                int y = Integer.parseInt(sy);
                double result = 0;

                int id = v.getId();
                if (id == R.id.btnPlus) {
                    result = x + y;
                } else if (id == R.id.btnMinus) {
                    result = x - y;
                } else if (id == R.id.btnMultiply) {
                    result = x * y;
                } else if (id == R.id.btnDivide) {
                    if (y == 0) {
                        txtResult.setText("Không thể chia cho 0");
                        return;
                    }
                    result = (double) x / y;
                } else if (id == R.id.btnModulo) {
                    if (y == 0) {
                        txtResult.setText("Không thể chia dư cho 0");
                        return;
                    }
                    result = x % y;
                }

                // In ra kết quả
                if (result == (int) result) {
                    txtResult.setText(String.valueOf((int) result));
                } else {
                    txtResult.setText(String.valueOf(result));
                }
            }
        };

        btnPlus.setOnClickListener(listener);
        btnMinus.setOnClickListener(listener);
        btnMultiply.setOnClickListener(listener);
        btnDivide.setOnClickListener(listener);
        btnModulo.setOnClickListener(listener);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        initControll();
    }
}