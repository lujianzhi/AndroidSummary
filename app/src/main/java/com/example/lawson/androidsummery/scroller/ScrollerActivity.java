package com.example.lawson.androidsummery.scroller;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
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

    private int deltaX = 240;
    private int x = 0;

    private final int startHandlerScroll = 1;
    private boolean directionRight = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case startHandlerScroll:
                    if (x <= -deltaX) {
                        directionRight = true;
                    } else if (x >= deltaX) {
                        directionRight = false;
                    }
                    //View左右循环移动
                    x = directionRight ? x + 1 : x - 1;
                    ivAnimScroll.scrollTo(x, 0);
                    handler.sendEmptyMessageDelayed(1, 10);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);

        sv = findViewById(R.id.my_scroller_view);
        ivAnimScroll = findViewById(R.id.iv_anim_scroll);

        ivNormalAnim = findViewById(R.id.iv_normal_anim);
        //补间动画
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.scroll_normal_ani);
        ivNormalAnim.startAnimation(animation);
        ivNormalAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "补间动画", Toast.LENGTH_SHORT).show();
            }
        });

        ivPropertyAnim = findViewById(R.id.iv_property_anim);
        //属性动画
        ObjectAnimator.ofFloat(ivPropertyAnim, "translationY", 100, 200, 0).setDuration(2000).start();
        ObjectAnimator.ofFloat(ivPropertyAnim, "translationX", 0, 150).setDuration(1000).start();
        ivPropertyAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "属性动画", Toast.LENGTH_SHORT).show();
            }
        });

        change = findViewById(R.id.change);
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
