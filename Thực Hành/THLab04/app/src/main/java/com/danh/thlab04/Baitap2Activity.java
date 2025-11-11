package com.danh.thlab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Baitap2Activity extends AppCompatActivity {

    private static final int[] ID_DRAWABLES = {
            R.drawable.ic_mess, R.drawable.ic_flight, R.drawable.ic_hospital,
            R.drawable.ic_hotel, R.drawable.ic_restaurant, R.drawable.ic_coctail,
            R.drawable.ic_store, R.drawable.ic_work, R.drawable.ic_time,
            R.drawable.ic_education, R.drawable.ic_movie
    };

    private static final int[] ID_TEXTS = {
            R.string.txt_mess, R.string.txt_flight, R.string.txt_hospital,
            R.string.txt_hotel, R.string.txt_restaurant, R.string.txt_coctail,
            R.string.txt_store, R.string.txt_work, R.string.txt_time,
            R.string.txt_education, R.string.txt_movie
    };

    private static final int[] VOCAB_ARRAYS = {
            R.array.vocab_mess, R.array.vocab_flight, R.array.vocab_hospital,
            R.array.vocab_hotel, R.array.vocab_restaurant, R.array.vocab_coctail,
            R.array.vocab_store, R.array.vocab_work, R.array.vocab_time,
            R.array.vocab_education, R.array.vocab_movie
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap2);

        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.removeAllViews();

        for (int i = 0; i < ID_DRAWABLES.length; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.item_topic, null);
            ImageView iv = v.findViewById(R.id.iv_topic);
            TextView tv = v.findViewById(R.id.tv_topic);

            iv.setImageResource(ID_DRAWABLES[i]);
            tv.setText(ID_TEXTS[i]);

            int index = i;
            v.setOnClickListener(view -> {
                String title = getString(ID_TEXTS[index]);
                String[] words = getResources().getStringArray(VOCAB_ARRAYS[index]);

                String toastMsg = title + "\n" + String.join(" • ", words);
                Toast.makeText(Baitap2Activity.this, toastMsg, Toast.LENGTH_SHORT).show();

                // cai tien
                Intent intent = new Intent(Baitap2Activity.this, DetailVocabActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("vocab_array", VOCAB_ARRAYS[index]);
                startActivity(intent);
            });

            lnMain.addView(v);
        }
    }
}