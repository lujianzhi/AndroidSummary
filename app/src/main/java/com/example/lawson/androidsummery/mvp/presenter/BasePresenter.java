package com.example.lawson.androidsummery.mvp.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Ian.Lu on 2017/8/18.
 * Project : AndroidSummary
 */

public class BasePresenter<BaseView> {

    private WeakReference<BaseView> views;

    protected void attachViews(BaseView views) {
        this.views = new WeakReference<BaseView>(views);
    }

    protected BaseView getViews() {
        return views.get();
    }

    protected void detachViews() {
        if (views != null) {
            views.clear();
            views = null;
        }
    }
}
