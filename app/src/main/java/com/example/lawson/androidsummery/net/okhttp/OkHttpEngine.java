package com.example.lawson.androidsummery.net.okhttp;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ian.Lu on 2017/8/8.
 * Project : AndroidSummary
 */

public class OkHttpEngine {

    private static OkHttpEngine instance;
    private OkHttpClient okHttpClient;
    private Handler handler;

    public static OkHttpEngine getInstance() {
        if (instance == null) {
            synchronized (OkHttpEngine.class) {
                if (instance == null) {
                    instance = new OkHttpEngine();
                }
            }
        }
        return instance;
    }

    private OkHttpEngine() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
        handler = new Handler();
    }

    public void sendGet(String url, MyDealBusiness myDealBusiness) {
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        handleCall(call, myDealBusiness);
    }

    private void handleCall(Call call, final MyDealBusiness myDealBusiness) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                myDealBusiness.failWorkThread(call, e);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myDealBusiness.failMainThread(call, e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                myDealBusiness.successWorkThread(call, response);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myDealBusiness.successMainThread(call, response);
                    }
                });
            }
        });
    }

}
