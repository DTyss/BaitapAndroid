package com.example.lab7.downloader;

import android.app.*;
import android.content.*;
import android.graphics.BitmapFactory;
import android.os.*;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import android.widget.RemoteViews;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadService extends Service {
    private volatile boolean isPaused = false;
    private volatile boolean isCanceled = false;
    private long downloaded = 0L;
    private long totalSize = -1L;
    private String urlStr;
    private String fileName;
    private File outFile;

    @Override public void onCreate() {
        super.onCreate();
        createChannel();
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;

        if (DM.ACTION_START.equals(action)) {
            urlStr = intent.getStringExtra(DM.EXTRA_URL);
            fileName = intent.getStringExtra(DM.EXTRA_NAME);
            if (fileName == null || fileName.trim().isEmpty()) {
                fileName = "download.bin";
            }
            outFile = new File(getFilesDir(), fileName); // internal storage
            isPaused = false; isCanceled = false;

            startForeground(DM.NOTI_ID, buildNoti(0, "Bắt đầu tải..."));
            new Thread(this::downloadLoop).start();
        } else if (DM.ACTION_PAUSE.equals(action)) {
            isPaused = true;
            updateNoti(-1, "Đã tạm dừng");
        } else if (DM.ACTION_RESUME.equals(action)) {
            if (isPaused) {
                isPaused = false;
                new Thread(this::downloadLoop).start();
                updateNoti(-1, "Tiếp tục tải...");
            }
        } else if (DM.ACTION_CANCEL.equals(action)) {
            isCanceled = true;
            stopSelf();
        }
        return START_NOT_STICKY;
    }

    private void downloadLoop() {
        HttpURLConnection conn = null;
        InputStream in = null;
        RandomAccessFile raf = null;
        try {
            if (outFile.exists()) downloaded = outFile.length();

            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(30000);

            // Resume bằng Range header
            if (downloaded > 0) {
                conn.setRequestProperty("Range", "bytes=" + downloaded + "-");
            }
            conn.connect();

            int code = conn.getResponseCode();
            // 206 = partial content khi resume; 200 = tải mới
            if (code == HttpURLConnection.HTTP_OK) {
                totalSize = conn.getContentLengthLong();
                downloaded = 0;
            } else if (code == HttpURLConnection.HTTP_PARTIAL) {
                long len = conn.getContentLengthLong();
                totalSize = downloaded + len;
            } else {
                updateNoti(-1, "Lỗi HTTP " + code);
                stopSelf();
                return;
            }

            in = new BufferedInputStream(conn.getInputStream());
            raf = new RandomAccessFile(outFile, "rw");
            raf.seek(downloaded);

            byte[] buf = new byte[8192];
            long lastUpdate = SystemClock.uptimeMillis();
            int n;
            while ((n = in.read(buf)) != -1) {
                if (isCanceled) { outFile.delete(); break; }
                while (isPaused) { SystemClock.sleep(200); if (isCanceled) break; }
                if (isCanceled) break;

                raf.write(buf, 0, n);
                downloaded += n;

                long now = SystemClock.uptimeMillis();
                if (now - lastUpdate > 500) {
                    int progress = totalSize > 0 ? (int) (downloaded * 100 / totalSize) : 0;
                    updateNoti(progress, "Đang tải...");
                    lastUpdate = now;
                }
            }

            if (isCanceled) {
                updateNoti(-1, "Đã hủy");
            } else if (!isPaused) {
                updateNoti(100, "Hoàn tất");
            }
        } catch (Exception e) {
            updateNoti(-1, "Lỗi: " + e.getMessage());
        } finally {
            try { if (in != null) in.close(); } catch (IOException ignored) {}
            try { if (raf != null) raf.close(); } catch (IOException ignored) {}
            if (!isPaused) stopSelf();
            if (conn != null) conn.disconnect();
        }
    }

    private Notification buildNoti(int progress, String status) {
        RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notify_download);
        rv.setTextViewText(R.id.txtTitle, status);
        if (progress >= 0) {
            rv.setProgressBar(R.id.progress, 100, progress, false);
        }

        // PendingIntents cho 3 nút
        rv.setOnClickPendingIntent(R.id.btnPause, pi(this, DM.ACTION_PAUSE));
        rv.setOnClickPendingIntent(R.id.btnResume, pi(this, DM.ACTION_RESUME));
        rv.setOnClickPendingIntent(R.id.btnCancel, pi(this, DM.ACTION_CANCEL));

        NotificationCompat.Builder b = new NotificationCompat.Builder(this, DM.CH_ID)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setContent(rv)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download));

        return b.build();
    }

    private void updateNoti(int progress, String status) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(DM.NOTI_ID, buildNoti(progress, status));
    }

    private PendingIntent pi(Context c, String action) {
        Intent i = new Intent(c, ActionReceiver.class).setAction(action);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        return PendingIntent.getBroadcast(c, action.hashCode(), i, flags);
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ch = new NotificationChannel(
                    DM.CH_ID, "Download Manager", NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(ch);
        }
    }

    @Nullable @Override public IBinder onBind(Intent intent) { return null; }
}
