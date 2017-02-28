package com.example.lawson.androidsummery.diyview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.MeasureUtil;

/**
 * Created by Ian.Lu on 2016/12/27.
 * Project : AndroidSummary
 */

public class MeasureView extends View {

    private TextPaint textPaint;
    private int width;
    private Bitmap backgroundImg;

    private enum Type {
        WIDTH, HEIGHT
    }

    public MeasureView(Context context) {
        super(context);
        init(context);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        width = MeasureUtil.getScreenSize((Activity) context)[0];

        backgroundImg = BitmapFactory.decodeResource(getResources(), R.drawable.four_color);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLUE);
        textPaint.setStrokeWidth(2);
        textPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMySize(widthMeasureSpec, Type.WIDTH), getMySize(widthMeasureSpec, Type.HEIGHT));
    }

    private int getMySize(int measureSpec, Type type) {
        int size = 0;

        int mode = MeasureSpec.getMode(measureSpec);
        int defaultSize = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            size = defaultSize;
        } else {
            if (type == Type.HEIGHT) {
                size = backgroundImg.getHeight() + getPaddingTop() + getPaddingBottom() + width / 10;
            } else {
                size = backgroundImg.getWidth() + getPaddingLeft() + getPaddingRight();
            }

            if (mode == MeasureSpec.AT_MOST) {
                size = Math.min(size, defaultSize);
            }
        }

        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backgroundImg, 0, 0, null);
        canvas.drawText("yoyoyo", getWidth() / 2, getHeight() + textPaint.ascent(), textPaint);
    }
}
