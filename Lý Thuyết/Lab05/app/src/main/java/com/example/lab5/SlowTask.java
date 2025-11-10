package com.example.lab5;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class SlowTask extends AsyncTask<Void, Integer, String> {

    private Context context;
    private TextView tvStatus;
    private ProgressDialog progressDialog;

    public SlowTask(Context context, TextView tvStatus) {
        this.context = context;
        this.tvStatus = tvStatus;
    }

    // Chạy trước khi thực hiện task nền
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.please_wait));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    // Chạy trên background thread (không được cập nhật UI trực tiếp)
    @Override
    protected String doInBackground(Void... voids) {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(2000); // Chờ 2 giây
                publishProgress(i); // Gửi tiến độ tới onProgressUpdate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Task Completed!";
    }

    // Cập nhật tiến độ trên UI thread
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        tvStatus.setText("Progress: " + progress + "/10");
    }

    // Chạy khi task hoàn thành
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        tvStatus.setText(result);
    }
}