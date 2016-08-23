package com.example.lawson.androidsummery.detectmemory;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {

//    private static class MyHandler extends Handler {
//
//        private WeakReference<HandlerActivity> activity;
//
//        public MyHandler(HandlerActivity activity) {
//            this.activity = new WeakReference<HandlerActivity>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            HandlerActivity handlerActivity = activity.get();
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
        setContentView(R.layout.activity_handler);

        //延时入队，会造成泄露
        handler.sendEmptyMessageDelayed(0,20000);
        //没有Message对象，不会泄露
//        handler.sendEmptyMessage(0);

        //使用静态内部类MyHandler，并且将Activity作为弱引用，不会造成内存泄露
//        MyHandler myHandler = new MyHandler(this);
//        myHandler.sendEmptyMessageDelayed(0, 20000);

    }
}
