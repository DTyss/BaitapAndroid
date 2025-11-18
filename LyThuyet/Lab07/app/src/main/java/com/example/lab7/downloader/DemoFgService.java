package com.example.lab7.downloader;

public class DemoFgService extends android.app.Service {
    @Override public int onStartCommand(android.content.Intent intent, int flags, int startId) {
        if ("demo.START".equals(intent.getAction())) {
            createCh(this);
            android.app.Notification n = new androidx.core.app.NotificationCompat.Builder(this, "dm_ch")
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setContentTitle("Demo FG Service")
                    .setContentText("Hello from foreground 👋")
                    .setOngoing(true)
                    .build();
            startForeground(999, n);  // <= bắt buộc <5s
            // Tự tắt sau 10s
            new android.os.Handler(getMainLooper()).postDelayed(this::stopSelf, 10_000);
        }
        return START_NOT_STICKY;
    }
    private static void createCh(android.content.Context c){
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            android.app.NotificationManager nm = (android.app.NotificationManager)c.getSystemService(NOTIFICATION_SERVICE);
            if (nm.getNotificationChannel("dm_ch")==null)
                nm.createNotificationChannel(new android.app.NotificationChannel("dm_ch","DM", android.app.NotificationManager.IMPORTANCE_LOW));
        }
    }
    @Override public android.os.IBinder onBind(android.content.Intent i){ return null; }
}
