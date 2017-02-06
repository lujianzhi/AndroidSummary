package com.example.lawson.androidsummery.thread.diy;

import android.util.Log;

/**
 * Created by Ian.Lu on 2017/1/17.
 * Project : AndroidSummary
 */

public class MyThread extends Thread {

    private String name;

    public MyThread(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            Log.i("Ian", "线程 : " + name + "运行 : " + i);
            if(i == 3){
                yield();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
