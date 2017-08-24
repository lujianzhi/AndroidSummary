package com.example.lawson.androidsummery.diyview.primary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/8/24.
 * Project : AndroidSummary
 */

public class PrimaryView extends View {

    private int defaultSize = 200;
    private int defaultColor = Color.GREEN;
    private Paint paint;

    public PrimaryView(Context context) {
        super(context);
        init(null);
    }

    public PrimaryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PrimaryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PrimaryView);
            defaultColor = typedArray.getColor(R.styleable.PrimaryView_viewColor, Color.GREEN);
            typedArray.recycle();
        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(defaultColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultSize, defaultSize);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultSize, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, defaultSize);
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }
    }

    private void drawCircle(Canvas canvas) {
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        canvas.drawCircle(getPaddingLeft() + width / 2, getPaddingTop() + height / 2, 100, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCircle(canvas);
    }
}
