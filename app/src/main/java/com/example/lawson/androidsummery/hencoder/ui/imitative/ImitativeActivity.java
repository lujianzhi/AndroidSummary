package com.example.lawson.androidsummery.hencoder.ui.imitative;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.hencoder.ui.imitative.views.UpDownTextView;

public class ImitativeActivity extends AppCompatActivity {

    private UpDownTextView upDownTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitative);
        upDownTextView = (UpDownTextView) findViewById(R.id.up_down_text_view);

        upDownTextView();
    }

    private void upDownTextView() {
        Button up = (Button) findViewById(R.id.up);
        Button down = (Button) findViewById(R.id.down);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDownTextView.upOrDownNumber(1);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDownTextView.upOrDownNumber(-1);
            }
        });
    }
}
