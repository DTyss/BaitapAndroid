package com.danh.thlab04;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailVocabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vocab);

        String title = getIntent().getStringExtra("title");
        int arrayId = getIntent().getIntExtra("vocab_array", R.array.vocab_mess);

        TextView tvTitle = findViewById(R.id.tv_title);
        ListView lv = findViewById(R.id.lv_vocab);

        tvTitle.setText(title);
        String[] words = getResources().getStringArray(arrayId);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, words);
        lv.setAdapter(adapter);
    }
}