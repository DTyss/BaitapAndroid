package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    GridView grid;
    ArrayList<com.example.lab8.Contributor> arr;
    ContributorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        grid = findViewById(R.id.gridContributors);

        arr = new ArrayList<>();
        arr.add(new Contributor(R.drawable.avatar1, "Maboo", "283,297"));
        arr.add(new Contributor(R.drawable.avatar2, "SameOldShawn", "252,433"));
        arr.add(new Contributor(R.drawable.avatar3, "Magnitude901", "164,935"));
        arr.add(new Contributor(R.drawable.avatar4, "Brandon", "100,466"));
        arr.add(new Contributor(R.drawable.avatar5, "Clement_RGF", "93,932"));
        arr.add(new Contributor(R.drawable.avatar6, "Nebja", "84,187"));

        adapter = new ContributorAdapter(this, arr);
        grid.setAdapter(adapter);
    }
}
