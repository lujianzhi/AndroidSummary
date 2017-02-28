package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ian.Lu on 2016/12/9.
 * Project : AndroidSummary
 */

public class CanvasView extends View {

    private Rect rect;
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;

    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        rect = new Rect(0, 0, 500, 500);
//        rect.union(250, 250, 750, 750);

        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(200, 200, 600, 600, paint);
//
//        canvas.saveLayerAlpha(300, 300, 500, 500, 50, Canvas.all_save_flag);
////        canvas.save();
//        canvas.rotate(5);
//        paint.setColor(Color.RED);
//        canvas.drawRect(300, 300, 400, 400, paint);
//        canvas.restore();

    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 300, mViewHeight / 2F - 300, mViewWidth / 2F + 300, mViewHeight / 2F + 300);
        canvas.drawColor(Color.YELLOW);

    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID2 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);
        canvas.restore();

    /*
     * 保存画布并旋转后绘制一个蓝色的矩形
     */
        int saveID3 = canvas.save(Canvas.MATRIX_SAVE_FLAG);

        // 旋转画布
        canvas.rotate(5);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 1700, mViewHeight / 2F + 700, mPaint);

        mPaint.setColor(Color.CYAN);
        canvas.drawRect(mViewWidth / 2F, mViewHeight / 2F, mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);
    }
}
