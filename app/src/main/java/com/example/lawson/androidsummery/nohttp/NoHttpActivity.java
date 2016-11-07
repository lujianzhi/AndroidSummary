package com.example.lawson.androidsummery.nohttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;

public class NoHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_http);

        ImageView kisum = (ImageView) findViewById(R.id.kisum);


    }
}
