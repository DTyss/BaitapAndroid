package com.danh.thlab2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class M001ActProfile extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE = 1;

    private ImageView imgPhone1;
    private TextView txtPhone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_profile);

        imgPhone1 = findViewById(R.id.img_phone1);
        txtPhone1 = findViewById(R.id.txt_phone1);

        // bấm icon gọi
        imgPhone1.setOnClickListener(v -> makePhoneCall());
        txtPhone1.setOnClickListener(v -> makePhoneCall());
    }

    private void makePhoneCall() {
        String phoneNumber = txtPhone1.getText().toString().trim();
        phoneNumber = phoneNumber.replaceAll("[^0-9]+", "");

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Phone number is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE
            );
            return;
        }

        // Cấp quyền -> gọi
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        try {
            startActivity(callIntent);
        } catch (SecurityException e) {
            Toast.makeText(this, "Call failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Kết quả xin quyền
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // user vừa cho quyền -> gọi tiếp
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission CALL_PHONE denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
