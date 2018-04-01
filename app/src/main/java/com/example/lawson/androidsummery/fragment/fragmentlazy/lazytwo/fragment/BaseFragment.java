package com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ian.Lu on 2018/3/27.
 * Project : AndroidSummary
 */

public abstract class BaseFragment extends Fragment {

    protected View mFragmentView;

    //视图是否初始化
    private boolean mViewInit;
    //强制需要每次都进行加载
    protected boolean mForceLoad;
    //数据已经加载完成
    private boolean mDataInited;

    /**
     * 每次调用这个方法，isVisibleToUser参数为true
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(setLayoutId(), container, false);
        init();
        return mFragmentView;
    }

    abstract int setLayoutId();

    /**
     * 模板方法
     */
    private void init() {
        //首先初始化view控件
        initView();
        //界面初始化完成
        mViewInit = true;
        //获取数据
        initData();
        //钩子函数
        hook();
    }

    abstract void initView();

    private void initData() {
        if (!mViewInit) {
            //没有初始化直接返回
            return;
        }

        if (getUserVisibleHint() || mForceLoad) {
            //对用户可见时，加载数据
            getData();
            mDataInited = true;
        } else {
            if (mDataInited) {
                //当已经加载完数据时但是界面已经对用户不可见，按需要可以做一些处理
                abandonData();
            }
        }
    }

    abstract void getData();

    abstract void abandonData();

    protected abstract void hook();


}
