package com.example.lawson.androidsummery;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lawson.androidsummery.beibei.hbaction.ActionManager;
import com.example.lawson.androidsummery.picviry.GYReceiver;
import com.example.lawson.androidsummery.thread.CrashHandler;
import com.getui.gysdk.GYManager;
import com.squareup.leakcanary.LeakCanary;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.DBCacheStore;
import com.yolanda.nohttp.cookie.DBCookieStore;

public class MyApplication extends Application {

    private String GY_APP_ID = "FzXMk6LUZD6wqRjnOREsWA";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        initLeakCanary();
        initNoHttp();
        initCrashHandler();
//        initFreeLine();
        initARouter();
        initAction();
        initGeTui();
    }

    private void initGeTui() {
        GYReceiver gyReceiver = new GYReceiver();
        IntentFilter filter = new IntentFilter("com.getui.gy.action." + GY_APP_ID);
        registerReceiver(gyReceiver, filter);
        GYManager.getInstance().init(this.getApplicationContext());
    }

    private void initAction() {
        ActionManager.init(this);
    }

    private void initARouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }


    private void initCrashHandler() {
        CrashHandler.getInstance().init(getApplicationContext());
    }

    private void initLeakCanary() {
        LeakCanary.install(this);
    }

    private void initNoHttp() {
        NoHttp.initialize(this, new NoHttp.Config().setConnectTimeout(1000)
                .setReadTimeout(2000)
                .setCacheStore(new DBCacheStore(this).setEnable(true))
                .setCookieStore(new DBCookieStore(this).setEnable(true))
                .setNetworkExecutor(new OkHttpNetworkExecutor()));
        Logger.setDebug(true);
        Logger.setTag("IanNoHttp");
    }
}
