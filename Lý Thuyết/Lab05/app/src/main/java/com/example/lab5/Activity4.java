package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Activity4 extends AppCompatActivity {

    private Button btnStartRxJava;
    private TextView tvResult;
    private ProgressBar pbRxJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        findViewByIds();
        initVariables();
    }

    private void findViewByIds() {
        btnStartRxJava = findViewById(R.id.btn_start_rxjava);
        tvResult = findViewById(R.id.tv_result);
        pbRxJava = findViewById(R.id.pb_rxjava);
    }

    private void initVariables() {
        btnStartRxJava.setOnClickListener(v -> performRxJavaTask());
    }

    private void performRxJavaTask() {
        tvResult.setText("Processing...");
        pbRxJava.setProgress(0);

        // Tạo Observable từ callable
        Observable.fromCallable(this::doBackgroundTask)
                // Thực hiện task trên I/O thread
                .subscribeOn(Schedulers.io())
                // Nhận kết quả trên main thread để cập nhật UI
                .observeOn(AndroidSchedulers.mainThread())
                // Subscribe với Observer
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Gọi khi đã subscribe
                    }

                    @Override
                    public void onNext(String result) {
                        // Xử lý kết quả
                        tvResult.setText(result);
                        pbRxJava.setProgress(100);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Xử lý lỗi
                        Toast.makeText(Activity4.this, "Error: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        tvResult.setText("Error occurred!");
                    }

                    @Override
                    public void onComplete() {
                        // Gọi khi task hoàn thành
                        Toast.makeText(Activity4.this, "Task Completed!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Phương thức chạy trên background thread
    private String doBackgroundTask() {
        try {
            // Mô phỏng task mất thời gian (5 giây)
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
            }
            return "RxJava Task Completed Successfully!";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}