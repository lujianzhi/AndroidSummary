package com.example.lawson.androidsummery.detectmemory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lawson.androidsummery.R;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadTimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_timer);

//        spawnThread();
        scheduleTimer();
    }


    private void spawnThread() {
        new Thread() {
            @Override public void run() {
                while(true);
            }
        }.start();
    }

    private void scheduleTimer() {
        //此处的Delay时间若是过了，则不会泄露，被GC回收。
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 20000);
    }

}
