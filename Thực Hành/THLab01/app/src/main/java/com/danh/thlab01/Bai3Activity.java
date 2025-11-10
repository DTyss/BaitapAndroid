package com.danh.thlab01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Bai3Activity extends AppCompatActivity {

    private EditText txtPhone;
    private Button btnCall, btnSendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        txtPhone = findViewById(R.id.txtPhone);
        btnCall = findViewById(R.id.btnCall);
        btnSendSMS = findViewById(R.id.btnSendSMS);

        // Nút gọi
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = txtPhone.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kích hoạt cuộc gọi
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));

                // Kiểm tra quyền gọi
                if (ActivityCompat.checkSelfPermission(Bai3Activity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Bai3Activity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }

                startActivity(callIntent);
            }
        });

        // Nút NHẮN TIN
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = txtPhone.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kích hoạt nhắn tin
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("sms:" + phoneNumber));
                smsIntent.putExtra("sms_body", "Hello from Lab1!");
                startActivity(smsIntent);
            }
        });
    }
}

