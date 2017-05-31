package com.example.lawson.androidsummery.diyview.learn2draw.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Ian.Lu on 2017/5/31.
 * Project : AndroidSummary
 * Android自定义动画酷炫的提交按钮
 */

public class SubmitButton extends View {

    //按钮的矩形
    private RectF rectF = new RectF();

    //View宽度
    private int width;

    //View高度
    private int height;

    //圆角半径
    private int circleAngle;

    //圆角矩形画笔
    private Paint paint;

    //默认两圆圆心之间的距离=需要移动的距离
    private int default_two_circle_distance;

    //Set
    private AnimatorSet animatorSet;

    //动画时长
    private int duration = 3000;

    //上移间距
    private int moveDistance = 300;

    //两圆圆心距离
    private int two_circle_distance;

    //矩形转化为圆角矩形的动画
    private ValueAnimator animatorRect2Angle;

    //矩形转化为圆角矩形的动画
    private ValueAnimator animationRect2Circle;

    //向上平移动画
    private ValueAnimator animationCircleUpMove;

    public SubmitButton(Context context) {
        super(context);
        init();
    }

    public SubmitButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SubmitButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(0xffbc7d53);

    }

    /**
     * 绘制带圆角的矩形
     */
    private void drawOval2Circle(Canvas canvas) {
        rectF.left = two_circle_distance;
        rectF.top = 0;
        rectF.right = width - two_circle_distance;
        rectF.bottom = height;

        canvas.drawRoundRect(rectF, circleAngle, circleAngle, paint);
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        setRect2AngleAnimation();
        setRect2CircleAnimation();
        setCircleUpMoveAnimation();

        mergeAnimation();
    }

    /**
     * 将圆角矩形转化为圆的动画
     */
    private void setRect2CircleAnimation() {
        animationRect2Circle = ObjectAnimator.ofInt(0, default_two_circle_distance);
        animationRect2Circle.setDuration(duration);
        animationRect2Circle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                two_circle_distance = (int) animation.getAnimatedValue();

                invalidate();
            }
        });
    }

    /**
     * 矩形转化为圆角矩形的动画
     */
    private void setRect2AngleAnimation() {
        animatorRect2Angle = ObjectAnimator.ofInt(0, default_two_circle_distance);
        animatorRect2Angle.setDuration(duration);
        animatorRect2Angle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleAngle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    /**
     * 向上平移动画
     */
    private void setCircleUpMoveAnimation() {
        float curY = this.getTranslationY();
        animationCircleUpMove = ObjectAnimator.ofFloat(this, "translationY", curY, curY - moveDistance);
        animationCircleUpMove.setDuration(duration);
        animationCircleUpMove.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    /**
     * 设置到一个集合里
     */
    private void mergeAnimation() {
        animatorSet = new AnimatorSet();
        animatorSet.play(animationCircleUpMove)
                .after(animatorRect2Angle)
                .after(animationRect2Circle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        default_two_circle_distance = (w - h) / 2;

        initAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawOval2Circle(canvas);
    }

    public void startAnimation() {
        animatorSet.start();
    }
}
