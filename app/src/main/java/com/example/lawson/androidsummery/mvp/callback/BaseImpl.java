package com.example.lawson.androidsummery.mvp.callback;

import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * Created by Ian.Lu on 2017/8/20.
 * Project : AndroidSummary
 */

public interface BaseImpl {

    boolean addDisposable(Disposable disposable);

    Context getContext();
}
