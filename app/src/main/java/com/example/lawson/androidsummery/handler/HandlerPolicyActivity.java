package com.example.lawson.androidsummery.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerPolicyActivity extends AppCompatActivity {

    private static String STRING_TAG = "string_tag";

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_policy);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainThread();
            }
        });
    }

    /**
     * 子线程
     */
    private void thread() {
        new Thread("子线程") {

            @Override
            public void run() {
                super.run();
                Looper.prepare();
                final Handler handler = new Handler();
                Log.i("Ian", "handler外 : " + handler.getLooper().getThread().getName());
                Log.i("Ian", "mainLooper : " + getMainLooper().getThread().getName());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Ian", "这是来自子线程的handler任务");
                        //在子线程中如果手动创建了Looper，那么应该在所有事情完成以后退出Looper，否则这个子线程会一直处于等待状态。
                        handler.getLooper().quit();
                    }
                });
                Looper.loop();
            }
        }.start();
    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            switch (msg.what) {
                case 1:
                    Log.i("Ian", "msg : " + msg.toString() + " ; msg.getData() : " + data.toString());
                    break;
            }
        }
    }

    /**
     * 主线程
     */
    private void mainThread() {
        MyHandler myHandler = new MyHandler();
        Message message = new Message();
        message.what = 1;
        message.arg1 = 2;
        message.arg2 = 3;
        Bundle data = new Bundle();
        data.putString(STRING_TAG, "这是来自主线程的handler任务");
        message.setData(data);
        myHandler.sendMessage(message);

    }
}
