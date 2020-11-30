package com.example.lawson.androidsummery.detectmemory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.lawson.androidsummery.R;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerLeakActivity extends AppCompatActivity {

//    private static class MyHandler extends Handler {
//
//        private WeakReference<HandlerLeakActivity> activity;
//
//        public MyHandler(HandlerLeakActivity activity) {
//            this.activity = new WeakReference<HandlerLeakActivity>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            HandlerLeakActivity handlerActivity = activity.get();
//            if (handlerActivity != null) {
//
//            }
//        }
//    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_leak);

        //延时入队，会造成泄露
        handler.sendEmptyMessageDelayed(0,20000);
        //没有Message对象，不会泄露
//        handler.sendEmptyMessage(0);

        //使用静态内部类MyHandler，并且将Activity作为弱引用，不会造成内存泄露
//        MyHandler myHandler = new MyHandler(this);
//        myHandler.sendEmptyMessageDelayed(0, 20000);

    }
}
