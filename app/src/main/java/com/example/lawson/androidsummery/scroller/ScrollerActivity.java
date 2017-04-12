package com.example.lawson.androidsummery.scroller;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

public class ScrollerActivity extends AppCompatActivity {

    private MyScrollerView sv;
    private ImageView ivNormalAnim;
    private ImageView ivPropertyAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);

        sv = (MyScrollerView) findViewById(R.id.my_scroller_view);
        sv.smoothScrollTo(-300, -100);

        ivNormalAnim = (ImageView) findViewById(R.id.iv_normal_anim);
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.scroll_normal_ani);
        ivNormalAnim.startAnimation(animation);
        ivNormalAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "补间动画", Toast.LENGTH_SHORT).show();
            }
        });

        ivPropertyAnim = (ImageView) findViewById(R.id.iv_property_anim);
        ObjectAnimator.ofFloat(ivPropertyAnim, "translationY", 0, 200).setDuration(1000).start();
        ObjectAnimator.ofFloat(ivPropertyAnim, "translationX", 0, 150).setDuration(1000).start();
        ivPropertyAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "属性动画", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
