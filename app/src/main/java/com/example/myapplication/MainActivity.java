package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    class MyHandler extends Handler {
        private WeakReference<MainActivity> activityWeakReference;

        public MyHandler(WeakReference<MainActivity> activityWeakReference) {
            this.activityWeakReference = activityWeakReference;
        }

        private int currentColor = 0;
        private int colors[] = new int[]{R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6};

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 123) {
                for (int i = 0; i < activityWeakReference.get().names.length; i++) {
                    activityWeakReference.get().textViews[i].setBackgroundResource(colors[(i + currentColor) % colors.length]);
                }
                currentColor++;
            }
        }
    }

    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private int names[] = new int[]{R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6};
    private TextView[] textViews = new TextView[names.length];

    private Handler handler = new MyHandler(new WeakReference<MainActivity>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = findViewById(names[i]);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(123);
            }
        }, 1000, 2000);

    }


}
