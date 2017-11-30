package com.example.lawson.androidsummery.hencoder.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/11/29.
 * Project : AndroidSummary
 */

public class ViewChapterFive extends View {

    private Bitmap bg;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Paint paint = new Paint();

    {
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.kisum3);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.voice_icon);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.kisum4);
    }

    public ViewChapterFive(Context context) {
        super(context);
    }

    public ViewChapterFive(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewChapterFive(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        //被前景盖住
        canvas.drawBitmap(bitmap2, 0, bitmap.getHeight(), paint);

        super.onDrawForeground(canvas);

        //盖住前景
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制view内容
        canvas.drawBitmap(bg, 0, 0, paint);
    }
}
