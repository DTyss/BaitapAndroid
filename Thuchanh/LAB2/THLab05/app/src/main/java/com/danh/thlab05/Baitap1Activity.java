package com.danh.thlab05;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class Baitap1Activity extends AppCompatActivity {

    private ImageView ivAnimal;
    private final int[] animIds = {
            R.anim.alpha, R.anim.scale, R.anim.translate, R.anim.rotate
    };
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap1);

        ivAnimal = findViewById(R.id.iv_animal);

        Button btnAlpha = findViewById(R.id.btn_alpha);
        Button btnScale = findViewById(R.id.btn_scale);
        Button btnTrans = findViewById(R.id.btn_trans);
        Button btnRotate = findViewById(R.id.btn_rotate);
        Button btnRandom = findViewById(R.id.btn_random);

        btnAlpha.setOnClickListener(v -> startAnim(R.anim.alpha));
        btnScale.setOnClickListener(v -> startAnim(R.anim.scale));
        btnTrans.setOnClickListener(v -> startAnim(R.anim.translate));
        btnRotate.setOnClickListener(v -> startAnim(R.anim.rotate));

        btnRandom.setOnClickListener(v -> {
            int randomAnim = animIds[random.nextInt(animIds.length)];
            startAnim(randomAnim);
        });
    }

    private void startAnim(int animRes) {
        Animation anim = AnimationUtils.loadAnimation(this, animRes);
        ivAnimal.startAnimation(anim);
    }
}