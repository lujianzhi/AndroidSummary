package com.example.lawson.androidsummery.animation.propertyanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

public class PropertyAnimationActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView pure_iv;
    private TextView combination_iv;
    private TextView xml_combination_iv;

    private Button btn;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private View btn_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        iv = findViewById(R.id.iv);
        pure_iv = findViewById(R.id.pure_iv);
        combination_iv = findViewById(R.id.combination_iv);
        xml_combination_iv = findViewById(R.id.xml_combination_iv);
        btn = findViewById(R.id.btn);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim1();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCombination();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeXMLCombination();
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView();
            }
        });
    }

    /**
     * 改变View属性
     */
    private void changeView() {
        //包装类，使得自己定义的属性asd，能够生效
//        ViewWrapper my = new ViewWrapper(btn_5);
//        ObjectAnimator.ofInt(my, "asd", 500).setDuration(1000).start();

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(1, 700);//这里的值，对应animation.getAnimatedValue()
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            private IntEvaluator intEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                Log.i("Ian", "AnimatedValue : " + currentValue);

                float fraction = animation.getAnimatedFraction();
                Log.i("Ian", "AnimatedFraction : " + fraction);

                btn_5.getLayoutParams().width = intEvaluator.evaluate(fraction, 10, 500);
                //当view确定自身已经不再适合现有的区域时，
                //该view本身调用这个方法要求parent view重新调用他的onMeasure onLayout来对重新设置自己位置。
                //特别的当view的layoutparameter发生改变，并且它的值还没能应用到view上，这时候适合调用这个方法。
                btn_5.requestLayout();
            }
        });
        valueAnimator.setDuration(1000).start();
    }

    /**
     * 加载xml
     */
    private void changeXMLCombination() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.property_anim);
        set.setTarget(xml_combination_iv);
        set.start();
    }

    /**
     * AnimatorSet组合动画
     */
    private void changeCombination() {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator colorAnimator = ObjectAnimator.ofInt(combination_iv, "backgroundColor", 0xFFFF8080, 0xFF8080FF);
        //颜色的类型估值器
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("Ian", "onAnimationUpdate -> getAnimatedFraction : " + animation.getAnimatedFraction());
            }
        });
        set.playTogether(
                ObjectAnimator.ofFloat(combination_iv, "rotationX", 0, 270),
                ObjectAnimator.ofFloat(combination_iv, "rotationY", 0, 90),
                ObjectAnimator.ofFloat(combination_iv, "translationX", 0, 90),
                ObjectAnimator.ofFloat(combination_iv, "translationY", 0, 90),
                ObjectAnimator.ofFloat(combination_iv, "scaleX", 1, 1.5F),
                ObjectAnimator.ofFloat(combination_iv, "scaleY", 1, 0.5F),
                colorAnimator
        );
        set.setDuration(5000).start();
    }

    /**
     * 颜色变化
     */
    private void changeColor() {
        //需要传入8进制的argb格式的颜色值
        ValueAnimator colorAnimator = ObjectAnimator.ofInt(pure_iv, "backgroundColor", 0xFFFF8080, 0xFF8080FF);
        colorAnimator.setDuration(2000);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        //无限循环
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //1->2 (REVERSE) 2->1 (REVERSE) 1->2
        //1->2 (不REVERSE) 1->2 (不REVERSE) 1->2
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimator.start();
    }

    /**
     * 平移变化
     */
    private void anim1() {
        ObjectAnimator.ofFloat(iv, "translationX", iv.getWidth(), 10, 20, 30, 40).setDuration(2000).start();
    }
}
