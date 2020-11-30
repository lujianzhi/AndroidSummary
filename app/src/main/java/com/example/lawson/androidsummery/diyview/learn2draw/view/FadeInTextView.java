package com.example.lawson.androidsummery.diyview.learn2draw.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import static android.R.attr.baseline;

/**
 * Created by Ian.Lu on 2017/6/12.
 * Project : AndroidSummary
 */

public class FadeInTextView extends TextView {

    private TextPaint textPaint;
    private StringBuilder sb;
    private StringBuilder content;
    private String[] strs;

    public FadeInTextView(Context context) {
        super(context);
        init();
    }

    public FadeInTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FadeInTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();

    }

    public void setText(String str) {
        content = new StringBuilder();
        sb = new StringBuilder(str);
        strs = new String[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            strs[i] = sb.substring(i, i + 1);
        }

        initAnimation();
    }

    private int tempIndex = -1;

    private void initAnimation() {
        ValueAnimator va = ValueAnimator.ofInt(0, sb.length() - 1);
        va.setInterpolator(new LinearInterpolator());
        va.setDuration(200 * sb.length());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();

                if (tempIndex != value) {
                    tempIndex = value;
                    content.append(strs[tempIndex]);

                    invalidate();
                }
//                content = strs[value];
            }
        });
        va.start();
    }

    private void initPaint() {
        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (content != null) {
            drawText(canvas, content.toString());
        }

    }

    private void drawText(Canvas canvas, String text) {
        Log.i("Ian", text);
        canvas.drawText(text, getPaddingLeft(), 100, textPaint);
    }
}
