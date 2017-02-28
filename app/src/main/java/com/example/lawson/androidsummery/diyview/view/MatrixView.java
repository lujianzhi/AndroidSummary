package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2016/12/6.
 * Project : AndroidSummary
 */

public class MatrixView extends ImageView {

    private Paint mPaint;
    private Bitmap bitmap;
    private int actionType = 0;//0:移动  1:缩放
    private Matrix matrix;
    private Matrix oldMatrix;

    public MatrixView(Context context) {
        super(context);
        init(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
//        mPaint = new Paint();
        matrix = new Matrix();
        oldMatrix = new Matrix();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
//        bitmap = Bitmap.createScaledBitmap(bitmap, MeasureUtil.getScreenSize((Activity) context)[0], MeasureUtil.getScreenSize((Activity) context)[1], true);
        setImageBitmap(bitmap);
//        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Matrix matrix = new Matrix();
//        matrix.setTranslate(500, 500);
//        matrix.postRotate(10);
//        bitmapShader.setLocalMatrix(matrix);
//        mPaint.setShader(bitmapShader);
    }

    float preX = 0;
    float preY = 0;
    float preX2 = 0;
    float preY2 = 0;
    float preScale;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        float x = event.getX();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: //单指
                preX = x;
                preY = y;
                actionType = 0;
                oldMatrix.set(matrix);
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                preX = x;
                preY = y;
                actionType = 1;
                preScale = (float) scaleVale(event.getX(0), event.getX(1), event.getY(0), event.getY(1));
                oldMatrix.set(matrix);
                break;

            case MotionEvent.ACTION_POINTER_UP:
                actionType = 2;
                break;

            case MotionEvent.ACTION_MOVE:
                matrix.set(oldMatrix);
                if (actionType == 0) {
                    matrix.postTranslate(event.getX() - preX, event.getY() - preY);
                } else if (actionType == 1 && event.getPointerCount() == 2) {
                    float scale = (float) scaleVale(event.getX(0), event.getX(1), event.getY(0), event.getY(1)) / preScale;
                    matrix.postScale(scale, scale, preX, preY);
                }
                break;
        }
        setImageMatrix(matrix);
        return true;
    }

    private double scaleVale(float preX, float nowX, float preY, float nowY) {
        float valueX = preX - nowX;
        float valueY = preY - nowY;
        return Math.sqrt(valueX * valueX + valueY * valueY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);
    }
}
