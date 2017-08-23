package com.example.lawson.androidsummery.mvp.model;

import android.text.TextUtils;
import android.util.Log;

import com.example.lawson.androidsummery.mvp.model.net.BaseRetrofit;
import com.example.lawson.androidsummery.mvp.model.net.NetApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ian.Lu on 2017/8/17.
 * Project : AndroidSummary
 */

public class BaseModel extends BaseRetrofit {
    //网络请求接口
    protected NetApi netApi;
    //请求参数
    protected Map<String, Object> paramsMap;

    public BaseModel(String url) {
        super(url);
        netApi = retrofit.create(NetApi.class);
        paramsMap = new HashMap<>();
    }

    protected void addParams(String key, Object value) {
        if (TextUtils.isEmpty(key) || value == null) {
            Log.i("Ian", "请输入正确格式的参数");
            return;
        }
        paramsMap.put(key, value);
    }

    protected void addParamMap(Map<String, Object> map) {
        if (map == null) {
            Log.i("Ian", "请输入正确格式的参数");
            return;
        }
        this.paramsMap.putAll(map);
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
