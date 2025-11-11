package com.danh.thlab05;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Baitap2Activity extends AppCompatActivity implements View.OnClickListener {

    private final int[] animIds = {R.anim.alpha, R.anim.scale, R.anim.translate, R.anim.rotate};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap2);

        findViewById(R.id.fr_mom).setOnClickListener(this);
        findViewById(R.id.fr_dad).setOnClickListener(this);
        findViewById(R.id.fr_crush).setOnClickListener(this);
        findViewById(R.id.fr_best_friend).setOnClickListener(this);
        findViewById(R.id.iv_dialer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // ÁP DỤNG ANIMATION TỪ BÀI 1
        int randomAnim = animIds[(int) (Math.random() * animIds.length)];
        Animation anim = AnimationUtils.loadAnimation(this, randomAnim);
        v.startAnimation(anim);

        if (v.getId() == R.id.iv_dialer) {
            startActivity(new Intent(Intent.ACTION_DIAL));
            return;
        }

        if (v instanceof FrameLayout) {
            String phone = (String) v.getTag();
            callPhone(phone);
        }
    }

    private void callPhone(String phone) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 101);
            Toast.makeText(this, "Cấp quyền gọi điện để tiếp tục!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }
}