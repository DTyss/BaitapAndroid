package com.danh.thlab10_11;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class M002StoryFrg extends Fragment {
    
    private Context mContext;
    private ArrayList<StoryEntity> listStory;
    private String topicName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m002_frg_story, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initViews(View v) {
        v.findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        v.findViewById(R.id.iv_back).setOnClickListener(v1 -> backToM001Screen());
        
        String displayName = convertToDisplayName(topicName);
        ((TextView) v.findViewById(R.id.tv_name)).setText(displayName);
        
        RecyclerView rv = v.findViewById(R.id.rv_story);
        ArrayList<StoryEntity> listStory = readStory();
        
        if (listStory.isEmpty()) {
            android.util.Log.e("M002StoryFrg", "No stories found for topic: " + topicName);
        }
        
        StoryAdapterNew adapter = new StoryAdapterNew(listStory, mContext);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
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

    private ArrayList<StoryEntity> readStory() {
        ArrayList<StoryEntity> listStory = new ArrayList<>();
        BufferedReader reader = null;
        
        try {
            String fileName = "story/" + topicName + ".txt";
            android.util.Log.d("M002StoryFrg", "Reading file: " + fileName);
            
            reader = new BufferedReader(new InputStreamReader(mContext.getAssets().open(fileName), "UTF-8"));
            String mLine;
            
            while (true) {
                String title = reader.readLine();
                if (title == null || title.trim().isEmpty()) break;
                
                android.util.Log.d("M002StoryFrg", "Found story title: " + title);
                
                StringBuilder content = new StringBuilder();
                while ((mLine = reader.readLine()) != null) {
                    if (mLine.contains("','0');")) {
                        content.append(mLine.replace("','0');", ""));
                        break;
                    }
                    content.append(mLine).append("\n");
                }
                
                StoryEntity storyEntity = new StoryEntity(topicName, title, content.toString().trim());
                listStory.add(storyEntity);
                
                android.util.Log.d("M002StoryFrg", "Added story: " + title);
            }
            
            reader.close();
            
        } catch (IOException e) {
            android.util.Log.e("M002StoryFrg", "Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        
        android.util.Log.d("M002StoryFrg", "Total stories loaded: " + listStory.size());
        return listStory;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    private void backToM001Screen() {
        ((MainActivity) getActivity()).backToM001Screen();
    }
}
