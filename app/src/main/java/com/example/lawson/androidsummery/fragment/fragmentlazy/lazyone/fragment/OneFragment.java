package com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2016/11/12.
 * Project : AndroidSummary
 */

public class OneFragment extends BaseFragment {
    @Override
    void initTag() {
        tag = "1";
        forceLoadData = true;
    }

    @Override
    View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    protected void loadDataView() {
        super.loadDataView();
        data.setVisibility(View.VISIBLE);
    }
}
