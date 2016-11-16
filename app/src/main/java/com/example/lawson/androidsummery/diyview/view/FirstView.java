package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ian.Lu on 2016/11/15.
 * Project : AndroidSummary
 */

public class FirstView extends View implements Runnable {
    private Paint mPaintFILL;
    private Paint mPaintSTROKE;
    private Paint mPaintFILLANDSTROKE;
    private int xPosition = 200;
    private int yPosition = 900;

    public FirstView(Context context) {
        super(context);
    }

    public FirstView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        //抗锯齿
        mPaintFILL = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置画笔样式
        mPaintFILL.setStyle(Paint.Style.FILL);
        //画笔颜色
        mPaintFILL.setColor(Color.GRAY);
        //画笔粗细，传入的值为px单位
        mPaintFILL.setStrokeWidth(80);

        //抗锯齿
        mPaintSTROKE = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置画笔样式
        mPaintSTROKE.setStyle(Paint.Style.STROKE);
        //画笔颜色
        mPaintSTROKE.setColor(Color.GRAY);
        //画笔粗细，传入的值为px单位
        mPaintSTROKE.setStrokeWidth(80);

        //抗锯齿
        mPaintFILLANDSTROKE = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置画笔样式
        mPaintFILLANDSTROKE.setStyle(Paint.Style.FILL_AND_STROKE);
        //画笔颜色
        mPaintFILLANDSTROKE.setColor(Color.GRAY);
        //画笔粗细，传入的值为px单位
        mPaintFILLANDSTROKE.setStrokeWidth(80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(200, 200, 120, mPaintFILL);
        canvas.drawCircle(200, 550, 120, mPaintSTROKE);
        canvas.drawCircle(xPosition, yPosition, 120, mPaintFILLANDSTROKE);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (xPosition < 400) {
                    xPosition += 50;
                } else {
                    xPosition = 200;
                }
                if (yPosition < 1600) {
                    yPosition += 50;
                } else {
                    yPosition = 800;
                }
                postInvalidate();
                invalidate();
                Thread.sleep(225);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
