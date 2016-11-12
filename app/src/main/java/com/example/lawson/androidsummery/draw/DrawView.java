package com.example.lawson.androidsummery.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by Ian.Lu on 2016/11/7.
 * Project : AndroidSummary
 */

public class DrawView extends View {

    private Canvas drawCanvas;
    private Bitmap drawBitmap;
    private Paint mPaint;
    private Path mPath;
    private float startX;
    private float startY;

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Bitmap.Config config = Bitmap.Config.ARGB_4444;
                drawBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), config);

                drawCanvas = new Canvas(drawBitmap);

                mPaint = new Paint();
                mPaint.setAntiAlias(true);
                mPaint.setColor(Color.BLACK);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(2);

                mPath = new Path();

            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(drawBitmap, 0, 0, null);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(startX, startY, x, y);
                break;

            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(mPath, mPaint);
                break;
        }

        invalidate();
        startX = x;
        startY = y;

        return true;
    }

    public Bitmap getDrawBitmap() {
        return drawBitmap;
    }
}
