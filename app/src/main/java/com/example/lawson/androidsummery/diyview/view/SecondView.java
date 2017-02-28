package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2016/11/16.
 * Project : AndroidSummary
 */

public class SecondView extends View {

    private Paint mPaint;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;
    private Bitmap background;
    private Bitmap background2;
    private Bitmap background3;

    private Path mPath;
    private Canvas mCanvas;
    private float startX;
    private float startY;

    public SecondView(Context context) {
        super(context);
    }

    public SecondView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();

    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint5 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        mPaint2.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        })));

        mPaint3.setColor(Color.GREEN);
        mPaint3.setColorFilter(new LightingColorFilter(0x000000FF, 0x0000FF000));
        mPaint4.setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN));
        background = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.img);


        background2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.kisum3);
        background3 = Bitmap.createBitmap(2560, 1600, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(background3);
        mCanvas.drawColor(Color.GRAY);
        mPaint5.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mPaint5.setAntiAlias(true);
        mPaint5.setStrokeCap(Paint.Cap.ROUND);
        mPaint5.setStrokeJoin(Paint.Join.ROUND);
        mPaint5.setStrokeWidth(50);
        mPaint5.setStyle(Paint.Style.STROKE);
        mPaint5.setARGB(128, 255, 0, 0);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, 0, 0, mPaint);
        canvas.drawBitmap(background, background.getWidth(), 0, mPaint2);
        canvas.drawBitmap(background, background.getWidth() * 2, 0, mPaint3);
        canvas.drawBitmap(background, background.getWidth() * 3, 0, mPaint4);

        canvas.drawBitmap(background2, 0, 0, null);
        canvas.drawBitmap(background3, 0, 0, null);

        mCanvas.drawPath(mPath, mPaint5);

//        canvas.drawCircle(200, 200, 200, mPaint);
//        canvas.drawCircle(200, 700, 200, mPaint3);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(startX, startY, x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        startX = x;
        startY = y;
        invalidate();
        return true;
    }
}
