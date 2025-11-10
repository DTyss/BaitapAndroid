package com.tys.lab04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo BroadcastReceiver
        broadcastReceiver = new MyBroadcastReceiver();
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        // Đăng ký BroadcastReceiver
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Hủy đăng ký BroadcastReceiver
        unregisterReceiver(broadcastReceiver);
    }

    // BroadcastReceiver để nhận tin nhắn SMS
    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Lấy thông tin từ Intent
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                Telephony.SmsMessage[] messages = new Telephony.SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = Telephony.SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                String sender = messages[0].getOriginatingAddress();
                String messageBody = messages[0].getMessageBody();
                // Hiển thị Toast thông báo có tin nhắn
                Toast.makeText(context, "New message from: " + sender, Toast.LENGTH_SHORT).show();
                // Cập nhật TextView với thông tin tin nhắn
                TextView textView = findViewById(R.id.tv_content);
                textView.setText("Sender: " + sender + "\nMessage: " + messageBody);
            }
        }
    }
}
