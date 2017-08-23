package com.example.lawson.androidsummery.mvp.model.net;

import android.util.Log;

import com.example.lawson.androidsummery.mvp.callback.BaseImpl;
import com.example.lawson.androidsummery.mvp.ui.NetProgressDialogHelper;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ian.Lu on 2017/8/18.
 * Project : AndroidSummary
 */

public abstract class BaseObserver<T> implements Observer<T> {

    protected abstract void mySuccess(@NonNull T obj);

    protected abstract void myFail(@NonNull Throwable e);

    protected abstract void myFinal();

    private NetProgressDialogHelper netProgressDialogHelper;

    private BaseImpl baseImpl;

    public BaseObserver(BaseImpl baseImpl) {
        this.baseImpl = baseImpl;
        if (netProgressDialogHelper == null) {
            netProgressDialogHelper = new NetProgressDialogHelper(baseImpl.getContext());
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull T obj) {
        if (obj == null) {
            Log.i("Ian", "数据转换为空");
            return;
        }
        mySuccess(obj);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        myFail(e);
    }

    @Override
    public void onComplete() {
        if (netProgressDialogHelper != null) {
            netProgressDialogHelper.sendEmptyMessage(NetProgressDialogHelper.HIDE);
        }
        myFinal();
    }
}
