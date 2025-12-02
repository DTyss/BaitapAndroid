package com.danh.thlab10_11;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class M001TopicFrg extends Fragment implements View.OnClickListener {
    
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m001_frg_topic, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initViews(View v) {
        LinearLayout lnMain = v.findViewById(R.id.ln_topic);
        lnMain.removeAllViews();
        
        try {
            String[] listItem = mContext.getAssets().list("photo");
            for (String fileName : listItem) {
                String name = fileName.substring(0, fileName.indexOf("."));
                String displayName = convertToDisplayName(name);
                View vTopic = LayoutInflater.from(mContext).inflate(R.layout.item_topic, null);
                
                ImageView ivTopic = vTopic.findViewById(R.id.iv_topic);
                TextView tvTopic = vTopic.findViewById(R.id.tv_topic);
                
                ivTopic.setImageBitmap(BitmapFactory.decodeStream(mContext.getAssets().open("photo/" + fileName)));
                tvTopic.setText(displayName);
                
                lnMain.addView(vTopic);
                
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) vTopic.getLayoutParams();
                params.bottomMargin = 40;
                vTopic.setLayoutParams(params);
                
                vTopic.setTag(name);
                vTopic.setOnClickListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String convertToDisplayName(String fileName) {
        switch (fileName) {
            case "ma_co_hon":
                return "Ma Cô Hồn";
            case "nguoi_dan_ba_ao_do":
                return "Người Đàn Bà Áo Đỏ";
            case "bup_be_ma":
                return "Búp Bê Ma";
            case "cau_hoang":
                return "Cầu Hoang";
            case "nha_hoang":
                return "Nhà Hoang";
            case "tieng_khoc_dem":
                return "Tiếng Khóc Đêm";
            case "guong_ma":
                return "Gương Ma";
            case "cay_da_co_thu":
                return "Cây Đa Cổ Thụ";
            default:
                return fileName.replace("_", " ");
        }
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).gotoM002Screen((String) v.getTag());
    }
}
