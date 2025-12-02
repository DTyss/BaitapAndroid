package com.example.lab8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContributorAdapter extends BaseAdapter {

    Context context;
    ArrayList<Contributor> list;

    public ContributorAdapter(Context context, ArrayList<Contributor> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null)
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_contributor, parent, false);

        Contributor c = list.get(i);

        ImageView img = view.findViewById(R.id.imgAvatar);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtScore = view.findViewById(R.id.txtScore);

        img.setImageResource(c.avatar);
        txtName.setText(c.name);
        txtScore.setText(c.score);

        return view;
    }
}
