package com.example.lawson.androidsummery.diyview.learn2draw.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Ian.Lu on 2017/6/7.
 * Project : AndroidSummary
 */

public class LoadingView extends View {

    private int tipHeight = 100;
    private int marginTipTop = 10;
    private int width;
    private int progress;
    private int tipMarginLeft;
    private int tipWidth = 200;
    private int tipTriangleHeight = 30;

    private Paint bgPaint;
    private Paint progressPaint;
    private Paint trianglePaint;
    private Paint tipPaint;
    private TextPaint textPaint;
    private Path tipPath;

    private RectF rectF;

    private ValueAnimator valueAnimator;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        initPaint();
        initTip();
        initAnimation();
    }

    private void initTip() {
        rectF = new RectF();
        tipPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawProgressLine(canvas);
        drawTip(canvas);
    }

    /**
     * 进度条
     */
    private void drawProgressLine(Canvas canvas) {
        //背景
        canvas.drawLine(getPaddingLeft(), tipHeight + marginTipTop,
                width, tipHeight + marginTipTop, bgPaint);

        //进度
        canvas.drawLine(getPaddingLeft(), tipHeight + marginTipTop,
                progress, tipHeight + marginTipTop, progressPaint);
    }

    /**
     * 提示框
     */
    private void drawTip(Canvas canvas) {
        rectF.set(tipMarginLeft, 0, tipWidth + tipMarginLeft, tipHeight - tipTriangleHeight);
        canvas.drawRoundRect(rectF, 10, 10, tipPaint);

        canvas.drawText("进度" + progress / 6 + "%", tipMarginLeft + tipWidth / 4, (tipHeight - tipTriangleHeight) / 2, textPaint);

        tipPath.moveTo(tipWidth / 2 + tipMarginLeft - tipTriangleHeight, tipHeight - tipTriangleHeight);
        tipPath.lineTo(tipWidth / 2 + tipMarginLeft, tipHeight);
        tipPath.lineTo(tipWidth / 2 + tipMarginLeft + tipTriangleHeight, tipHeight - tipTriangleHeight);
        canvas.drawPath(tipPath, trianglePaint);
        tipPath.reset();

    }

    private void initPaint() {
        bgPaint = new Paint();
        bgPaint.setColor(Color.GRAY);
        bgPaint.setStrokeWidth(10);
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);

        progressPaint = new Paint();
        progressPaint.setColor(Color.BLUE);
        progressPaint.setStrokeWidth(10);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.FILL);

        tipPaint = new Paint();
        tipPaint.setColor(Color.RED);
        tipPaint.setStrokeWidth(20);
        tipPaint.setAntiAlias(true);
        tipPaint.setStyle(Paint.Style.FILL);

        trianglePaint = new Paint();
        trianglePaint.setColor(Color.GREEN);
        trianglePaint.setStrokeWidth(20);
        trianglePaint.setAntiAlias(true);
        trianglePaint.setStyle(Paint.Style.FILL);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
    }

    private void initAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, width);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (int) animation.getAnimatedValue();
                if (progress > tipWidth / 2 && progress < width - tipWidth / 2) {
                    tipMarginLeft = progress - tipWidth / 2;
                }
                invalidate();
            }
        });
        valueAnimator.start();
    }

}
