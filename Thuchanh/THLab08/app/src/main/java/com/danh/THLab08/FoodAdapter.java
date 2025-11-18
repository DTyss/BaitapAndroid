package com.example.lab8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    Context context;
    ArrayList<Food> list;

    public FoodAdapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView imgFood;
        TextView txtName, txtDes, txtPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_food, parent, false);

            holder = new ViewHolder();
            holder.imgFood = convertView.findViewById(R.id.imgFood);
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtDes = convertView.findViewById(R.id.txtDes);
            holder.txtPrice = convertView.findViewById(R.id.txtPrice);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Food f = list.get(position);

        holder.txtName.setText(f.getName());
        holder.txtDes.setText(f.getDescription());
        holder.txtPrice.setText(String.valueOf(f.getPrice()));
        holder.imgFood.setImageResource(f.getImg());

        return convertView;
    }
}
