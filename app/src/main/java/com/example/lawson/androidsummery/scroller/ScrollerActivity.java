package com.example.lawson.androidsummery.scroller;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

public class ScrollerActivity extends AppCompatActivity {

    private MyScrollerView sv;
    private ImageView ivNormalAnim;
    private ImageView ivPropertyAnim;
    private ImageView ivAnimScroll;
    private Button change;

    private int deltaX = 300;
    private int x = 0;

    private final int startHandlerScroll = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case startHandlerScroll:
                    if (x < deltaX) {
                        x++;
                        ivAnimScroll.scrollTo(x, 0);
                        handler.sendEmptyMessageDelayed(1, 100);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);

        sv = (MyScrollerView) findViewById(R.id.my_scroller_view);
        ivAnimScroll = (ImageView) findViewById(R.id.iv_anim_scroll);

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
        ObjectAnimator.ofFloat(ivPropertyAnim, "translationY", 100, 200, 0).setDuration(2000).start();
        ObjectAnimator.ofFloat(ivPropertyAnim, "translationX", 0, 150).setDuration(1000).start();
        ivPropertyAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "属性动画", Toast.LENGTH_SHORT).show();
            }
        });

        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.smoothScrollTo(-300, -100);
                startAni();
            }
        });

        handler.sendEmptyMessageDelayed(startHandlerScroll, 1000);
    }

    private void startAni() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400, 0).setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int value = (int) animation.getAnimatedValue();
                ivAnimScroll.scrollTo((int) -(ivAnimScroll.getWidth() * fraction), 0);
                Log.i("Ian", "time : " + System.currentTimeMillis() + " ; fraction : " + fraction + " ; value : " + value);
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //handler不remove掉的话会造成内存泄露
        handler.removeMessages(startHandlerScroll);
        handler = null;
    }
}
