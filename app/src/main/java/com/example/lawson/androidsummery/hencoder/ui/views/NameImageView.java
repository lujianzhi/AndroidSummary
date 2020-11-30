package com.example.lawson.androidsummery.hencoder.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.example.lawson.androidsummery.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by Ian.Lu on 2017/12/3.
 * Project : AndroidSummary
 */

public class NameImageView extends AppCompatImageView {

    private TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private StaticLayout staticLayout;
    private String nameString;
    private PointF position = new PointF();

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
        setTranslationX(position.x * getWidth());
        setTranslationY(position.y * getHeight());
        invalidate();
    }

    public NameImageView(Context context) {
        super(context);
    }

    public NameImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NameImageView);
        nameString = typedArray.getString(R.styleable.NameImageView_view_name);
        typedArray.recycle();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        staticLayout = new StaticLayout(nameString, 0, nameString.length(), paint, getWidth() - 20, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(10, 30);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}
