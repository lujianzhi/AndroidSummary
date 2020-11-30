package com.example.lawson.androidsummery.fourcomponent.activityabout;

import android.os.Bundle;
import android.util.Log;

import com.example.lawson.androidsummery.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MyFragmentActivity extends AppCompatActivity {

    private static String TAG = "Activity - ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Ian", TAG + "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment, new TestFragment(), "f1")
//                .commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.i("Ian", TAG + "onAttachFragment");
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ian", TAG + "onStart");
    }

    @Override
    protected void onResume() {
        Log.i("Ian", TAG + "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("Ian", TAG + "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Ian", TAG + "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Ian", TAG + "onDestroy");
        super.onDestroy();
    }
}
