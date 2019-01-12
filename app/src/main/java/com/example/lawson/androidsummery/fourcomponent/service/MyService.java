package com.example.lawson.androidsummery.fourcomponent.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ian.Lu on 2017/9/12.
 * Project : AndroidSummary
 */

public class MyService extends Service {
    private ExecutorService cachedThreadPool;
    private MyBinder myBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        cachedThreadPool = Executors.newCachedThreadPool();
        Log.i("Ian", "MyService -> onCreate ; Thread:" + Thread.currentThread().getName());
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Ian", "MyService -> onBind ; Thread:" + Thread.currentThread().getName());
        return myBinder;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Ian", "MyService -> onDestroy ; Thread:" + Thread.currentThread().getName());
    }
    public class MyBinder extends Binder {
        public void saySomething(final String say) {
            Log.i("Ian", "MyService -> Binder -> saySomething:" + say + " ; Thread:" + Thread.currentThread().getName());
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.i("Ian", "MyService -> Binder -> cachedThreadPool saySomething:" + say + " ; Thread:" + Thread.currentThread().getName());
                }
            });
        }
    }
}
