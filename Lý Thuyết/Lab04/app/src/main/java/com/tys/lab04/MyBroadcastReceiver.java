package com.tys.lab04;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Lấy thông tin từ Intent
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String sender = messages[0].getOriginatingAddress();
            String messageBody = messages[0].getMessageBody();
            // Hiển thị Toast thông báo có tin nhắn
            Toast.makeText(context, "New message from: " + sender, Toast.LENGTH_SHORT).show();
            // Cập nhật TextView với thông tin tin nhắn
            TextView textView = ((Activity) context).findViewById(R.id.tv_content);
            textView.setText("Sender: " + sender + "\nMessage: " + messageBody);
        }
    }
}
