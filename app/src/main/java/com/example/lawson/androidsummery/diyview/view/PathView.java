package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ian.Lu on 2016/12/9.
 * Project : AndroidSummary
 */

public class PathView extends View {

    private Paint paint;
    private TextPaint textPaint;
    private Path path;
    private Region regionA, regionB;

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint();
        textPaint = new TextPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        textPaint.setTextSize(20);
        textPaint.setColor(Color.RED);

        regionA = new Region(100, 100, 300, 300);
        regionB = new Region(200, 200, 400, 400);


//        path.moveTo(500, 500);
//        path.addRect(100, 100, 500, 500, Path.Direction.CW);
//        path.quadTo(700, 900, 900, 400);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPath(path, paint);
//        canvas.drawTextOnPath("哟哟,Hi,Ian.哟哟,Hi,Ian.哟哟,Hi,Ian.哟哟,Hi,Ian.哟哟,Hi,Ian.", path, 0, 0, textPaint);

        canvas.drawColor(Color.BLUE);
        canvas.save();

//        canvas.clipRegion(regionA);

//        canvas.clipRegion(regionB, Region.Op.DIFFERENCE);

        canvas.drawColor(Color.RED);

        canvas.restore();

        canvas.drawRect(100, 100, 300, 300, paint);
        canvas.drawRect(200, 200, 400, 400, paint);
    }
}
