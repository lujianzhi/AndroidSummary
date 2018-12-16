package com.example.lawson.androidsummery.animation.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

public class ViewAnimationActivity extends AppCompatActivity {

    private ImageView iv;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        iv = findViewById(R.id.iv);
        ll = findViewById(R.id.ll);

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeAnimation();
            }
        });

        findViewById(R.id.change_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeCodeAnimation();
            }
        });

        findViewById(R.id.layout_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeLayoutAnimation();
            }
        });
    }

    private void executeLayoutAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.item_view_animation);//子元素的动画动画
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setDelay(0.5F);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_REVERSE);
        ll.setLayoutAnimation(layoutAnimationController);
        ll.startLayoutAnimation();
    }

    private void executeCodeAnimation() {
        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(true);
        set.setDuration(5000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        set.addAnimation(alphaAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(100, 500, 100, 300);
        set.addAnimation(translateAnimation);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("Ian", "onAnimationStart");
                Toast.makeText(getBaseContext(), "onAnimationStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("Ian", "onAnimationEnd");
                Toast.makeText(getBaseContext(), "onAnimationEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("Ian", "onAnimationRepeat");
                Toast.makeText(getBaseContext(), "onAnimationRepeat", Toast.LENGTH_SHORT).show();
            }
        });

        iv.startAnimation(set);
    }

    private void executeAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.view_animation);
        iv.startAnimation(animation);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }
}
