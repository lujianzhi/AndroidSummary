package com.example.lawson.androidsummery.animation.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;

public class FrameAnimationActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        iv = findViewById(R.id.iv);

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeAnimation();
            }
        });
    }

    private void executeAnimation() {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.start();
    }
}
