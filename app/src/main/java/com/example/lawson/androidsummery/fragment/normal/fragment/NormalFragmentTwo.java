package com.example.lawson.androidsummery.fragment.normal.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.FragmentLazyLoadTwoActivity;

/**
 * Created by Ian.Lu on 2018/3/27.
 * Project : AndroidSummary
 */

public class NormalFragmentTwo extends BaseNormalFragment {

    public static NormalFragmentTwo getInstance() {
        return new NormalFragmentTwo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal_two, container, false);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(FragmentLazyLoadTwoActivity.PATH).navigation();
            }
        });
        return view;
    }
}
