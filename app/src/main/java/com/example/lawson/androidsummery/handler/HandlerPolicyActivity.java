package com.example.lawson.androidsummery.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HandlerPolicyActivity extends AppCompatActivity {

    private static String STRING_TAG = "string_tag";

    private MyHandler myHandler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_policy);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

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

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerPost();
            }
        });

        button4.setOnClickListener((view) -> {
            handlerThread();
        });

        button5.setOnClickListener((view) -> {
            idleHandler();
        });
    }

    private void idleHandler() {
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.i("ian", "IdleHandler 开始执行");
                return false; //返回true则一直保留该IdleHandler；返回false则在运行完一次后移除该IdleHandler
            }
        });
    }

    private void handlerThread() {
        HandlerThread ht = new HandlerThread("ian");
        ht.start();

        new Thread() {
            @Override
            public void run() {
                super.run();

                Handler workHandler = new Handler(ht.getLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 1:
                                Log.i("ian", "Thread" + Thread.currentThread() + " - obj:" + msg.obj);
                                break;
                        }
                        ht.quit();
                    }
                };

                Message message = new Message();
                message.what = 1;
                message.obj = "aaaaa";
                workHandler.sendMessage(message);
            }
        }.start();
    }

    private void handlerPost() {
        myHandler.post(() -> {
            Log.i("ian", "post");
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
                case 2:
                    Log.i("Ian", "msg : " + msg.toString() + " ; msg.getData() : " + data.toString());
                    break;
            }
        }
    }

    /**
     * 主线程
     */
    private void mainThread() {
        Message message = Message.obtain();
        message.what = 1;
        message.arg1 = 2;
        message.arg2 = 3;
        Bundle data = new Bundle();
        data.putString(STRING_TAG, "这是来自主线程的handler任务");
        message.setData(data);
        myHandler.sendMessage(message);

    }
}
