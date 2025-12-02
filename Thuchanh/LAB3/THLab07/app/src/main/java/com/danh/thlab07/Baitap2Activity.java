package com.danh.thlab07;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Baitap2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap2);

        Button btnToast = findViewById(R.id.btnCustomToast);
        Button btnDialog = findViewById(R.id.btnCustomDialog);

        btnToast.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, null);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        });

        btnDialog.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_dialog, null);

            AlertDialog dialog = new AlertDialog.Builder(Baitap2Activity.this)
                    .setView(dialogView)
                    .setCancelable(false)
                    .create();

            dialogView.findViewById(R.id.btnCloseDialog).setOnClickListener(close -> dialog.dismiss());
            dialog.show();
        });
    }
}