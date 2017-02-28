package com.example.lawson.androidsummery.diyview;

import android.app.Activity;
import android.os.Bundle;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.view.FirstView;

public class DIYViewActivity extends Activity {

    private FirstView firstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyview);

//        firstView = (FirstView) findViewById(R.id.first_view);
//
//        new Thread(firstView).start();

    }
}
