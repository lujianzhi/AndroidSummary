package com.example.lawson.androidsummery.diyview.hideview;

import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类似RecyclerView的ScrollListener，可以添加多个监听
 * <p>
 * Created by Ian.Lu on 2018/5/31.
 * Project : BeiBeiProject
 */
public class ListViewAddableOnScrollListener implements AbsListView.OnScrollListener {

    private List<AbsListView.OnScrollListener> mExtraScrollListeners;

    public ListViewAddableOnScrollListener() {
        mExtraScrollListeners = new ArrayList<>();
    }

    /**
     * 添加滚动监听
     *
     * @param onScrollListener 滚动监听
     */
    public void addScrollListeners(AbsListView.OnScrollListener onScrollListener) {
        mExtraScrollListeners.add(onScrollListener);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        for (AbsListView.OnScrollListener onScrollListener : mExtraScrollListeners) {
            onScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        for (AbsListView.OnScrollListener onScrollListener : mExtraScrollListeners) {
            onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }
}
