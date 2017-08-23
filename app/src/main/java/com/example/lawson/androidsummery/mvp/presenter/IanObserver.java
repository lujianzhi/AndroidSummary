package com.example.lawson.androidsummery.mvp.presenter;

import android.util.Log;

import com.example.lawson.androidsummery.mvp.callback.BaseImpl;
import com.example.lawson.androidsummery.mvp.model.net.BaseObserver;

import io.reactivex.annotations.NonNull;

/**
 * Created by Ian.Lu on 2017/8/20.
 * Project : AndroidSummary
 */

public abstract class IanObserver<News> extends BaseObserver<News> {
    public IanObserver(BaseImpl baseImpl) {
        super(baseImpl);
    }

    @Override
    protected void myFail(@NonNull Throwable e) {
        Log.i("Ian", "myFail : " + e.getMessage());
    }

    @Override
    protected void myFinal() {

    }
}
