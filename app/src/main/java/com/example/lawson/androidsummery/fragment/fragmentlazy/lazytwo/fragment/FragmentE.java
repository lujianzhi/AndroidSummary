package com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment;

import android.util.Log;
import android.widget.TextView;
import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2018/4/1.
 * Project : AndroidSummary
 */

public class FragmentE extends BaseFragment {

    private TextView textView;

    @Override
    int setLayoutId() {
        return R.layout.fragment;
    }

    @Override
    void initView() {
        textView = (TextView) mFragmentView.findViewById(R.id.text_view);
    }

    @Override
    void initData() {
        textView.setText("Fragment     E      获取到了数据Fragment     E      获取到了数据Fragment     E      获取到了数据");
    }

    @Override
    void abandonData() {
        Log.i("Ian", "Fragment     E      清除数据");
    }

    @Override
    protected void hook() {

    }

}
