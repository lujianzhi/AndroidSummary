package com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment;

import android.util.Log;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2018/4/1.
 * Project : AndroidSummary
 */

public class FragmentC extends BaseFragment {

    private TextView textView;

    @Override
    int setLayoutId() {
        return R.layout.fragment;
    }

    @Override
    void initView() {
        textView = mFragmentView.findViewById(R.id.text_view);
    }

    @Override
    void initData() {
        textView.setText("Fragment     C      获取到了数据Fragment     C      获取到了数据Fragment     C      获取到了数据");
    }

    @Override
    void abandonData() {
        Log.i("Ian", "Fragment     C      清除数据");
    }

    @Override
    protected void hook() {

    }

}
