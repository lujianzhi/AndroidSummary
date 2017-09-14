package com.example.lawson.androidsummery;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.antfortune.freeline.FreelineCore;
import com.example.lawson.androidsummery.thread.CrashHandler;
import com.github.mzule.activityrouter.router.RouterCallback;
import com.github.mzule.activityrouter.router.RouterCallbackProvider;
import com.squareup.leakcanary.LeakCanary;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.DBCacheStore;
import com.yolanda.nohttp.cookie.DBCookieStore;

public class MyApplication extends Application implements RouterCallbackProvider {

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

    @Override
    public RouterCallback provideRouterCallback() {
        return new RouterCallback() {
            @Override
            public void notFound(Context context, Uri uri) {
                Toast.makeText(context, "notFound" + uri.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void beforeOpen(Context context, Uri uri) {
                Toast.makeText(context, "beforeOpen" + uri.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterOpen(Context context, Uri uri) {
                Toast.makeText(context, "afterOpen" + uri.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error(Context context, Uri uri, Throwable e) {
                Toast.makeText(context, "error" + uri.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
