package com.example.lawson.androidsummery.diyview.learn2draw.view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

/**
 * Created by Ian.Lu on 2017/8/9.
 * Project : AndroidSummary
 */

public class DisplayUtil {

    /**
     * 将 px 转换为 dip 或 dp， 保证尺寸大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {

        /* density 是屏幕比例因子， 以 160dpi（1px = 1dp） 为标准 density 值为1，320dpi（2px = 1dp） 中 density 值为 2（320/160） */
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 將 dip 或 dp 转换为 px, 保证尺寸大小不变
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将 px 转换为 sp， 保证尺寸大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {

        /* scaledDensity，字体的比例因子，类似 density， 会根据用户偏好返回不同的值*/
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将 sp 转换为 px， 保证尺寸大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int sp2px(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue * fontScale + 0.5f);
    }

    /**
     * 使用系统工具类 TypedValue 帮助把 数值 转换到 px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /* height 单位是 px */
    public static void showDropView(Context context, View view, float height) {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }
        view.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = dropAnim(view, 0, (int) height);
        valueAnimator.start();
    }

    public static void hideDropView(Context context, final View view, float height) {
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        ValueAnimator valueAnimator = dropAnim(view, (int) height, 0);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });

        valueAnimator.start();
    }

    private static ValueAnimator dropAnim(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return valueAnimator;
    }

    /**
     * 圆形展开动画
     *
     * @param v
     * @param centerX
     * @param centerY
     * @param startRadius
     * @param endRadius
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator circularRevealAnim(View v, int centerX, int centerY, float startRadius, float endRadius) {
        return circularRevealAnim(v, centerX, centerY, startRadius, endRadius, 1500);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator circularRevealAnim(View v, int centerX, int centerY, float startRadius, float endRadius, long animDuration) {
        Animator animator = ViewAnimationUtils.createCircularReveal(v,
                centerX, centerY, startRadius, endRadius);
        animator.setDuration(1500);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
        return animator;
    }

}
