package com.danh.thlab04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.Random;

public class EmojiFragment extends Fragment implements View.OnClickListener {

    private final int[] emojiIds = {
            R.drawable.ic_angry, R.drawable.ic_baffle, R.drawable.ic_beauty,
            R.drawable.ic_boss, R.drawable.ic_choler, R.drawable.ic_dribble,
            R.drawable.ic_look_down, R.drawable.ic_sure, R.drawable.ic_tire,
            R.drawable.ic_happy, R.drawable.ic_cry, R.drawable.ic_love
    };

    private final int[] viewIds = {
            R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5,
            R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9
    };

    private final Random random = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_emoji, container, false);

        // Gán sự kiện click cho 9 emoji
        for (int id : viewIds) {
            v.findViewById(id).setOnClickListener(this);
        }

        // Nút Random
        Button btnRandom = v.findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(view -> randomAllEmoji(v));

        return v;
    }

    @Override
    public void onClick(View v) {
        ImageView iv = (ImageView) v;
        int drawableId = Integer.parseInt((String) iv.getTag());
        showEmojiToast(drawableId);
    }

    private void randomAllEmoji(View root) {
        for (int id : viewIds) {
            ImageView iv = root.findViewById(id);
            int randomEmoji = emojiIds[random.nextInt(emojiIds.length)];
            iv.setImageResource(randomEmoji);
            iv.setTag(randomEmoji);
        }
        showEmojiToast(R.drawable.ic_happy); // bonus toast
    }

    private void showEmojiToast(int drawableId) {
        Toast toast = new Toast(requireContext());
        ImageView iv = new ImageView(requireContext());
        iv.setImageResource(drawableId);
        iv.setPadding(32, 32, 32, 32);
        iv.setBackgroundResource(R.drawable.bg_emoji_circle);

        toast.setView(iv);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 150);
        toast.show();
    }
}