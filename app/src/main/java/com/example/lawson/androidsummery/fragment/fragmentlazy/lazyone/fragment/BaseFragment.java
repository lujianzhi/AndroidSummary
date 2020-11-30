package com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Ian.Lu on 2016/11/12.
 * Project : AndroidSummary
 */

public abstract class BaseFragment extends Fragment {

    protected String tag;
    protected TextView data;
    protected boolean isViewVisible;
    protected boolean isViewInitiated;
    protected boolean isDataInitiated;
    protected boolean forceLoadData;

    abstract void initTag();

    abstract View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected void loadDataView() {
        Log.i("Ian", tag + "-> loadDataView");
        isDataInitiated = true;
    }

    protected void loadData() {
        if (isViewInitiated && isViewVisible && (!isDataInitiated || forceLoadData)) {
            loadDataView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isViewVisible = isVisibleToUser;
        initTag();
        Log.i("Ian", tag + "-> setUserVisibleHint-isVisibleToUser ->" + isVisibleToUser);
        loadData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Ian", tag + "-> onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Ian", tag + "-> onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Ian", tag + "-> onCreateView");
        View view = createView(inflater, container, savedInstanceState);
        isViewInitiated = true;
        data = view.findViewById(R.id.data);
        loadData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Ian", tag + "-> onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Ian", tag + "-> onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Ian", tag + "-> onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Ian", tag + "-> onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Ian", tag + "-> onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Ian", tag + "-> onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Ian", tag + "-> onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Ian", tag + "-> onDetach");
    }

}
