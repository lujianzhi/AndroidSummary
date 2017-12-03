package com.example.lawson.androidsummery.hencoder.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/12/3.
 * Project : AndroidSummary
 */

public class NameImageView extends AppCompatImageView {

    private TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private String nameString;

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(nameString, 10, 30, paint);
    }
}
