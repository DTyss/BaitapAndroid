package com.danh.thlab04;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Baitap1Activity extends AppCompatActivity {

    private EditText edtEmail, edtPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap1);

        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String pass = edtPass.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty()) {
                showCustomToast("Vui lòng nhập đầy đủ!", false);
            } else {
                showCustomToast("Đăng nhập thành công!\nEmail: " + email + "\nMật khẩu: " + pass, true);
            }
        });
    }

    private void showCustomToast(String message, boolean success) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, null);

        ImageView ivIcon = layout.findViewById(R.id.iv_toast_icon);
        TextView tvMsg = layout.findViewById(R.id.tv_toast_msg);

        ivIcon.setImageResource(success ? R.drawable.ic_check : R.drawable.ic_error);
        tvMsg.setText(message);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);
        toast.show();
    }
}