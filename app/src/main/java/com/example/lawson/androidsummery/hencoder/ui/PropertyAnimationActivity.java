package com.example.lawson.androidsummery.hencoder.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.hencoder.ui.evaluator.HsvEvaluator;
import com.example.lawson.androidsummery.hencoder.ui.evaluator.PointFEvaluator;
import com.example.lawson.androidsummery.hencoder.ui.views.ArcAnimationView;

public class PropertyAnimationActivity extends AppCompatActivity {

    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_hencoder);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationSet();
            }
        });

        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        animationSet();
    }

    private void animationSet() {
        viewPropertyAnimator();

        arcAnimationView();

        setInterpolatorAndListener();

        typeEvaluator();

        ofObject();

        propertyValuesHolder();

        animatorSet();

        ofKeyframe();
    }

    private void ofKeyframe() {
        ArcAnimationView imageView13 = findViewById(R.id.imageView13);
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.6F, 360);
        Keyframe keyframe3 = Keyframe.ofFloat(1, 250);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("degree", keyframe1, keyframe2, keyframe3);
        ObjectAnimator.ofPropertyValuesHolder(imageView13, holder).setDuration(3000).start();
    }

    private void animatorSet() {
        ImageView imageView10 = findViewById(R.id.imageView10);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView10, "alpha", 0, 1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView10, "translationX", 0, 100);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView10, "translationY", 0, 100);
        AnimatorSet animatorSet1 = new AnimatorSet();
        //依次执行
        animatorSet1.playSequentially(objectAnimator1, objectAnimator2, objectAnimator3);
        animatorSet1.setDuration(3000).start();

        ImageView imageView11 = findViewById(R.id.imageView11);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(imageView11, "alpha", 0, 1);
        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(imageView11, "translationX", 0, 100);
        ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(imageView11, "translationY", 0, 100);
        AnimatorSet animatorSet2 = new AnimatorSet();
        //同时执行
        animatorSet2.playTogether(objectAnimator4, objectAnimator5, objectAnimator6);
        animatorSet2.setDuration(3000).start();

        ImageView imageView12 = findViewById(R.id.imageView12);
        ObjectAnimator objectAnimator7 = ObjectAnimator.ofFloat(imageView12, "alpha", 0, 1);
        ObjectAnimator objectAnimator8 = ObjectAnimator.ofFloat(imageView12, "translationX", 0, 100);
        ObjectAnimator objectAnimator9 = ObjectAnimator.ofFloat(imageView12, "translationY", 0, 100);
        AnimatorSet animatorSet3 = new AnimatorSet();
        //以play中的动画为基础
        //以下代码意思是，objectAnimator7与objectAnimator9，在objectAnimator8之前，同时播放
        animatorSet3.play(objectAnimator7).before(objectAnimator8).with(objectAnimator9);
        animatorSet3.setDuration(3000).start();
    }

    private void propertyValuesHolder() {
        ImageView imageView9 = findViewById(R.id.imageView9);

        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView9, holder1, holder2, holder3);
        animator.setDuration(3000);
        animator.start();
    }

    private void ofObject() {
        final ImageView imageView8 = findViewById(R.id.imageView8);
        //在自定义View中写上position属性，该属性是PointF类型。
        //根据PointF对象写一个PointFEvaluator
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(imageView8, "position",
                new PointFEvaluator(), new PointF(0, 0), new PointF(0.3F, 0.7F), new PointF(1, 1));
        objectAnimator.setDuration(3000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imageView8.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator.ofObject(imageView8, "position",
                                new PointFEvaluator(), new PointF(1, 1), new PointF(0, 0)).start();
                    }
                }, 500);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    private void typeEvaluator() {
        ArcAnimationView imageView6 = findViewById(R.id.imageView6);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(imageView6, "color", 0xffff0000, 0xff0000ff);
        objectAnimator.setDuration(3000);
        //设置颜色计算器，否则会出现闪烁的现象。使用ArgbEvaluator时，颜色只是从0xffff0000变黑了一下，直接到了0xff0000ff
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.start();
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView6, "degree", 0, 360, 137);
        objectAnimator2.setDuration(3000);
        objectAnimator2.start();

        ArcAnimationView imageView7 = findViewById(R.id.imageView7);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofInt(imageView7, "color", 0xffff0000, 0xff0000ff);
        objectAnimator3.setDuration(3000);
        //自定义颜色计算器。颜色平滑过渡，不存在黑一下过渡
        objectAnimator3.setEvaluator(new HsvEvaluator());
        objectAnimator3.start();
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(imageView7, "degree", 0, 360, 137);
        objectAnimator4.setDuration(3000);
        objectAnimator4.start();

    }

    private void setInterpolatorAndListener() {
        final ImageView imageView4 = findViewById(R.id.imageView4);
        final ImageView imageView5 = findViewById(R.id.imageView5);

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
                imageView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView2.animate().translationX(0);
                    }
                }, 500);
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
                imageView3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator.ofFloat(imageView3, "translationX", 0).start();
                    }
                }, 500);
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
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();

        imageView4.animate().translationX(200).setInterpolator(new CycleInterpolator(3)).setDuration(3000).withEndAction(new Runnable() {
            @Override
            public void run() {
                imageView4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView4.animate().translationX(0);
                    }
                }, 500);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.lineTo(0.1F, 0.1F);
            path.lineTo(0.4F, 0.7F);
            path.lineTo(0.8F, 0.9F);
            path.lineTo(1.0F, 1.0F);
            imageView5.animate().translationX(200).setInterpolator(new PathInterpolator(path)).setDuration(3000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    imageView5.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView5.animate().translationX(0);
                        }
                    }, 500);
                }
            });
        }
    }

    private void arcAnimationView() {
        final ArcAnimationView arcAnimationView = findViewById(R.id.arcAnimationView);
        //自定义degree属性时，ObjectAnimator会调用degree相应的setter方法
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(arcAnimationView,
                "degree", 0, 360, 137);
        objectAnimator.setDuration(3000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                arcAnimationView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator.ofFloat(arcAnimationView,
                                "degree", 360);
                    }
                }, 500);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    private void viewPropertyAnimator() {
        final ImageView imageView = findViewById(R.id.imageView1);
        imageView.animate().translationX(200).alpha(0.0f).scaleX(0.0f).scaleY(0.0f).setDuration(3000).withEndAction(new Runnable() {
            @Override
            public void run() {
                imageView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.animate().translationX(0).alpha(1).scaleX(1).scaleY(1);
                    }
                }, 500);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        imageView2.animate().setUpdateListener(null);
        imageView2.animate().setListener(null);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView3, "translationX", 200);
        objectAnimator.addUpdateListener(null);
        objectAnimator.addListener(null);
    }
}
