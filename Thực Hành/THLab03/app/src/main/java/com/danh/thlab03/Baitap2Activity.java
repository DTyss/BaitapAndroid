package com.danh.thlab03;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class Baitap2Activity extends AppCompatActivity {

    private TextView tvVi, tvEn, tvFr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap2);

        tvVi = findViewById(R.id.tv_vi);
        tvEn = findViewById(R.id.tv_en);
        tvFr = findViewById(R.id.tv_fr);

        tvVi.setOnClickListener(v -> setLocale("vi"));
        tvEn.setOnClickListener(v -> setLocale("en"));
        tvFr.setOnClickListener(v -> setLocale("fr"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_language, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_vietnam) {
            setLocale("vi");
            return true;
        } else if (id == R.id.menu_english) {
            setLocale("en");
            return true;
        } else if (id == R.id.menu_french) {
            setLocale("fr");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        recreate();
    }
}