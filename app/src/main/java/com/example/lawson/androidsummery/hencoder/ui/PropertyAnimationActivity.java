package com.example.lawson.androidsummery.hencoder.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.hencoder.ui.views.ArcAnimationView;

public class PropertyAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_hencoder);

        animationSet();
    }

    private void animationSet() {
        viewPropertyAnimator();

        arcAnimationView();

        setInterpolatorAndListener();
    }

    private void setInterpolatorAndListener() {
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);

        //PropertyAnimator
        imageView2.animate().translationX(200).setInterpolator(new DecelerateInterpolator()).setDuration(3000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("Ian", "PropertyAnimator - onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("Ian", "PropertyAnimator - onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i("Ian", "PropertyAnimator - onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i("Ian", "PropertyAnimator - onAnimationRepeat");
            }
        }).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("Ian", "PropertyAnimator - onAnimationUpdate");
            }
        }).withEndAction(new Runnable() {
            @Override
            public void run() {
                Log.i("Ian", "PropertyAnimator - withEndAction");
            }
        }).withStartAction(new Runnable() {
            @Override
            public void run() {
                Log.i("Ian", "PropertyAnimator - withStartAction");
            }
        });

        //ObjectAnimator
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView3, "translationX", 200);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("Ian", "ObjectAnimator - onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("Ian", "ObjectAnimator - onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i("Ian", "ObjectAnimator - onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i("Ian", "ObjectAnimator - onAnimationRepeat");
            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("Ian", "ObjectAnimator - onAnimationUpdate");
            }
        });
        objectAnimator.setRepeatCount(2);
        objectAnimator.start();

        imageView4.animate().translationX(200).setInterpolator(new CycleInterpolator(3)).setDuration(3000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.lineTo(0.1F, 0.1F);
            path.lineTo(0.4F, 0.7F);
            path.lineTo(0.8F, 0.9F);
            path.lineTo(1.0F, 1.0F);
            imageView5.animate().translationX(200).setInterpolator(new PathInterpolator(path)).setDuration(3000);
        }
    }

    private void arcAnimationView() {
        ArcAnimationView arcAnimationView = (ArcAnimationView) findViewById(R.id.arcAnimationView);
        //自定义degree属性时，ObjectAnimator会调用degree相应的setter方法
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(arcAnimationView,
                "degree", 0, 360, 137);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    private void viewPropertyAnimator() {
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.animate().translationX(200).alpha(0.0f).scaleX(0.0f).scaleY(0.0f).setDuration(3000);
    }
}
