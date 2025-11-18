package com.example.lab7.downloader;

public final class DM {
    private DM() {}

    // Notification
    public static final String CH_ID   = "dm_channel"; // id kênh notification
    public static final int    NOTI_ID = 1001;         // id thông báo cố định cho tiến trình tải

    // Action string (mặc định nên prefix theo "namespace" app cho unique)
    public static final String ACTION_START  = "dm.ACTION_START";
    public static final String ACTION_PAUSE  = "dm.ACTION_PAUSE";
    public static final String ACTION_RESUME = "dm.ACTION_RESUME";
    public static final String ACTION_CANCEL = "dm.ACTION_CANCEL";

    // Extra keys (key cho Intent putExtra/getStringExtra)
    public static final String EXTRA_URL  = "dm.EXTRA_URL";
    public static final String EXTRA_NAME = "dm.EXTRA_NAME"; // tên file output
}
