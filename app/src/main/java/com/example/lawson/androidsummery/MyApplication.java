package com.example.lawson.androidsummery;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.antfortune.freeline.FreelineCore;
import com.example.lawson.androidsummery.thread.CrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.DBCacheStore;
import com.yolanda.nohttp.cookie.DBCookieStore;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
        initNoHttp();
        initCrashHandler();
        initFreeLine();
        initARouter();
    }

    private void initARouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }

    private void initFreeLine() {
        FreelineCore.init(this);
    }

    private void initCrashHandler() {
        CrashHandler.getInstance().init(getApplicationContext());
    }

    private void initLeakCanary() {
        LeakCanary.install(this);
    }

    private void initNoHttp() {
        NoHttp.initialize(this,
                new NoHttp.Config()
                        .setConnectTimeout(1000)
                        .setReadTimeout(2000)
                        .setCacheStore(new DBCacheStore(this).setEnable(true))
                        .setCookieStore(new DBCookieStore(this).setEnable(true))
                        .setNetworkExecutor(new OkHttpNetworkExecutor()));
        Logger.setDebug(true);
        Logger.setTag("IanNoHttp");
    }
}
