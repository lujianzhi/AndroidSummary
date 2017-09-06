package com.example.lawson.androidsummery.thread.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Ian.Lu on 2017/9/6.
 * Project : AndroidSummary
 */

public class MyIntentService extends IntentService {
    public static final String TASK_ACTION = "task_action";
    public static final String TASK_ONE = "task_one";
    public static final String TASK_TWO = "task_two";
    public static final String TASK_THREE = "task_three";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String taskAction = intent.getStringExtra(TASK_ACTION);
            if (TASK_ONE.equals(taskAction)) {
                Log.i("Ian", TASK_ONE);
            } else if (TASK_TWO.equals(taskAction)) {
                Log.i("Ian", TASK_TWO);
            } else if (TASK_THREE.equals(taskAction)) {
                Log.i("Ian", TASK_THREE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Ian", "MyIntentService -> onDestroy");
    }
}
