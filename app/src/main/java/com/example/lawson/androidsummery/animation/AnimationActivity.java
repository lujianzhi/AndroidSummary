package com.example.lawson.androidsummery.animation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.animation.frameanimation.FrameAnimationActivity;
import com.example.lawson.androidsummery.animation.propertyanimation.PropertyAnimationActivity;
import com.example.lawson.androidsummery.animation.viewanimation.ViewAnimationActivity;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        findViewById(R.id.view_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimationActivity.this, ViewAnimationActivity.class));
                //enter_anim : 新的Activity进入时的动画
                //exit_anim : 旧的Activity出去时的动画
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });

        findViewById(R.id.frame_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimationActivity.this, FrameAnimationActivity.class));
            }
        });

        findViewById(R.id.property_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimationActivity.this, PropertyAnimationActivity.class));
            }
        });
    }
}
