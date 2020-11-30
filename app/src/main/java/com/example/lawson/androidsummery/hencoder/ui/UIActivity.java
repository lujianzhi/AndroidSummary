package com.example.lawson.androidsummery.hencoder.ui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.hencoder.ui.imitative.ImitativeActivity;

public class UIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), PropertyAnimationActivity.class));
            }
        });

        findViewById(R.id.imitative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ImitativeActivity.class));
            }
        });
    }
}
