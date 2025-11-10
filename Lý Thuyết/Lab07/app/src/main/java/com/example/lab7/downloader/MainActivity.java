package com.example.lab7.downloader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.lab7.downloader.DM;
import com.example.lab7.downloader.DownloadService;

public class MainActivity extends AppCompatActivity {
    EditText edtUrl;
    Button btnDownload, btnPause, btnResume, btnCancel;

    ActivityResultLauncher<String> reqNoti = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), granted -> {}
    );

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUrl = findViewById(R.id.edtUrl);
        btnDownload = findViewById(R.id.btnDownload);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnCancel = findViewById(R.id.btnCancel);

        askNotificationIfNeeded();

        btnDownload.setOnClickListener(v -> {
            Toast.makeText(this, "CLICKED 🚀", Toast.LENGTH_SHORT).show();
            android.util.Log.d("DM", "btnDownload clicked");
            // Start demo service
            Intent i = new Intent(this, DemoFgService.class);
            i.setAction("demo.START");
            androidx.core.content.ContextCompat.startForegroundService(this, i);
        });

        btnPause.setOnClickListener(v -> sendAction(DM.ACTION_PAUSE));
        btnResume.setOnClickListener(v -> sendAction(DM.ACTION_RESUME));
        btnCancel.setOnClickListener(v -> sendAction(DM.ACTION_CANCEL));
    }

    private void sendAction(String action) {
        Intent i = new Intent(this, DownloadService.class);
        i.setAction(action);
        startService(i);
    }

    private String guessName(String url) {
        int p = url.lastIndexOf('/');
        return (p >= 0 && p < url.length()-1) ? url.substring(p+1) : "download.bin";
    }

    private void askNotificationIfNeeded() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                reqNoti.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
}
