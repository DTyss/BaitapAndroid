package com.example.lab7.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ActionReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context ctx, Intent i) {
        String action = i.getAction();
        Intent svc = new Intent(ctx, DownloadService.class);
        if (DM.ACTION_PAUSE.equals(action)) {
            svc.setAction(DM.ACTION_PAUSE);
        } else if (DM.ACTION_RESUME.equals(action)) {
            svc.setAction(DM.ACTION_RESUME);
        } else if (DM.ACTION_CANCEL.equals(action)) {
            svc.setAction(DM.ACTION_CANCEL);
        }
        ctx.startService(svc);
    }
}

