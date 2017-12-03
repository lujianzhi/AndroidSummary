package com.example.lawson.androidsummery.hencoder.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.lawson.androidsummery.R;

import java.util.Locale;

/**
 * Created by Ian.Lu on 2017/11/30.
 * Project : AndroidSummary
 */

public class ArcAnimationView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    //自定义属性
    private float degree = 0;
    private String nameString;

    /**
     * set方法，供ObjectAnimator使用
     */
    public void setDegree(float degree) {
        this.degree = degree;
        //触发重绘，使其执行onDraw()
        invalidate();
    }

    public float getDegree() {
        return degree;
    }

    {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);

        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(30);
        textPaint.setStyle(Paint.Style.STROKE);
    }

    public ArcAnimationView(Context context) {
        super(context);
    }

    public ArcAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NameImageView);
        nameString = typedArray.getString(R.styleable.NameImageView_view_name);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //degree是自定义的属性，弧线根据degree来确定弧度
        canvas.drawArc(20, 20, 200, 200, 0, degree, false, paint);
        canvas.drawText(String.format(Locale.CHINA, "%.0f", degree / 3.6) + "%",
                85, 120, textPaint);
        canvas.drawText(nameString, 20, getHeight()-30, textPaint);
    }
}
