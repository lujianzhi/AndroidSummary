package com.example.lawson.androidsummery.thread.diy;

import android.util.Log;

/**
 * Created by Ian.Lu on 2017/1/17.
 * Project : AndroidSummary
 */

public class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Log.i("Ian", "线程" + name + "，执行任务" + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
