package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ian.Lu on 2016/12/8.
 * Project : AndroidSummary
 */

public class MultiCircleView extends View {

    private int view_Width;//view宽度
    private float stroke_width = 1 / 256F;
    private float line_blank_space = 2 / 128F;
    private float line_length = 3 / 32F;
    private float radius_of_large_circle = 3 / 32F;
    private float radius_of_small_circle = 5 / 64F;
    private float radius_of_rac = 7 / 64F;
    private float degree_30 = 30;
    private float degree_10 = 10;
    private float degree_135 = 135;

    private float centerX;
    private float centerY;

    private Paint mPaint;
    private Paint arcPaint;

    public MultiCircleView(Context context) {
        super(context);
        init(context);
    }

    public MultiCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MultiCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.YELLOW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.view_Width = w;
        initValues();
    }

    private void initValues() {
        stroke_width = stroke_width * view_Width;
        line_blank_space = line_blank_space * view_Width;
        line_length = line_length * view_Width;
        radius_of_large_circle = radius_of_large_circle * view_Width;
        radius_of_small_circle = radius_of_small_circle * view_Width;
        radius_of_rac = radius_of_rac * view_Width;
        mPaint.setStrokeWidth(stroke_width);
        mPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(stroke_width);
        arcPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        centerX = canvas.getWidth() / 2;
        centerY = canvas.getHeight() / 2;

        drawCenterCircle(canvas);

        drawTopLeftCircle(canvas);

        drawTopRightCircle(canvas);

        drawBottomLeftCircle(canvas);

        drawBottomCircle(canvas);

        drawBottomRightCircle(canvas);
    }

    /**
     * 右下的圆
     *
     * @param canvas
     */
    private void drawBottomRightCircle(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY + radius_of_large_circle);
        canvas.rotate(degree_10);
        canvas.drawLine(radius_of_large_circle + line_blank_space, 0, radius_of_large_circle + line_blank_space + line_length, 0, mPaint);
        canvas.drawCircle(radius_of_large_circle + line_blank_space * 2 + line_length + radius_of_small_circle, 0, radius_of_small_circle, mPaint);
        canvas.restore();
    }

    /**
     * 底部的圆
     *
     * @param canvas
     */
    private void drawBottomCircle(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY + radius_of_large_circle);
        canvas.drawLine(0, radius_of_large_circle + line_blank_space, 0, radius_of_large_circle + line_blank_space + line_length, mPaint);
        canvas.drawCircle(0, radius_of_large_circle + line_blank_space * 2 + line_length + radius_of_small_circle, radius_of_small_circle, mPaint);
        canvas.restore();
    }

    /**
     * 左下的圆
     *
     * @param canvas
     */
    private void drawBottomLeftCircle(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY + radius_of_large_circle);
        canvas.rotate(-degree_10);
        canvas.drawLine(-radius_of_large_circle - line_blank_space, 0, -radius_of_large_circle - line_blank_space - line_length, 0, mPaint);
        canvas.drawCircle(-radius_of_large_circle - line_blank_space * 2 - line_length - radius_of_small_circle, 0, radius_of_small_circle, mPaint);
        canvas.restore();
    }

    /**
     * 右上的圆
     *
     * @param canvas
     */
    private void drawTopRightCircle(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY + radius_of_large_circle);
        canvas.rotate(degree_30);
        canvas.drawLine(0, -radius_of_large_circle, 0, -radius_of_large_circle - line_length, mPaint);
        canvas.drawCircle(0, -radius_of_large_circle * 2 - line_length, radius_of_large_circle, mPaint);

        drawArc(canvas);

        canvas.restore();
    }

    /**
     * 画弧
     *
     * @param canvas
     */
    private void drawArc(Canvas canvas) {
        canvas.save();
        canvas.translate(0, -2 * radius_of_large_circle - line_length);
        canvas.rotate(-degree_30);
        RectF rectF = new RectF(-view_Width / 8, -view_Width / 8, view_Width / 8, view_Width / 8);
        canvas.drawArc(rectF, -degree_30, -degree_135, true, arcPaint);
        canvas.restore();
    }

    /**
     * 左上部分圆组合
     *
     * @param canvas
     */
    private void drawTopLeftCircle(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY + radius_of_large_circle);
        canvas.rotate(-degree_30);
        canvas.drawLine(0, -radius_of_large_circle, 0, -line_length - radius_of_large_circle, mPaint);
        canvas.drawCircle(0, -line_length - radius_of_large_circle * 2, radius_of_large_circle, mPaint);
        canvas.drawLine(0, -line_length - radius_of_large_circle * 3, 0, -line_length * 2 - radius_of_large_circle * 3, mPaint);
        canvas.drawCircle(0, -line_length * 2 - radius_of_large_circle * 4, radius_of_large_circle, mPaint);
        canvas.restore();
    }

    /**
     * 画中心圆
     *
     * @param canvas
     */
    private void drawCenterCircle(Canvas canvas) {
        canvas.drawCircle(centerX, centerY + radius_of_large_circle, radius_of_large_circle, mPaint);
    }
}
