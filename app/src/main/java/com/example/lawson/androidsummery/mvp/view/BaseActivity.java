package com.example.lawson.androidsummery.mvp.view;

import android.os.Bundle;
import android.view.View;

import com.example.lawson.androidsummery.mvp.callback.BaseImpl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ian.Lu on 2017/8/20.
 * Project : AndroidSummary
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView, View.OnClickListener, BaseImpl {

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutResId() == 0) {
            throw new IllegalArgumentException("layoutId不能为0");
        }
        setContentView(getLayoutResId());

        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        initViews();
        attachPresenter();
    }

    @Override
    public boolean addDisposable(Disposable disposable) {
        return mCompositeDisposable.add(disposable);
    }

    abstract int getLayoutResId();

    abstract void initViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }
}
