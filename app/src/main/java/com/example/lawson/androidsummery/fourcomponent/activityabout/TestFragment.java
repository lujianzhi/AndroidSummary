package com.example.lawson.androidsummery.fourcomponent.activityabout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lawson.androidsummery.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Ian.Lu on 2019/1/9.
 * Project : AndroidSummary
 */
public class TestFragment extends Fragment {

    private static String TAG = "Fragment - ";

    @Override
    public void onAttach(Context context) {
        Log.i("Ian", TAG + "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("Ian", TAG + "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Ian", TAG + "onCreateView");
        return inflater.inflate(R.layout.test_fragment_layout, container, false);
    }

    @Override
    public void onStart() {
        Log.i("Ian", TAG + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("Ian", TAG + "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("Ian", TAG + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("Ian", TAG + "onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i("Ian", TAG + "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i("Ian", TAG + "onDetach");
        super.onDetach();
    }
}
