package com.example.lawson.androidsummery.diyview.learn2draw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.learn2draw.view.FadeInTextView;

public class FadeInTextViewActivity extends AppCompatActivity {

    private FadeInTextView fadeInTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_in_text_view);

        fadeInTV = (FadeInTextView) findViewById(R.id.fade_in_tv);
        fadeInTV.setText("这是一个很屌的View");
    }
}
