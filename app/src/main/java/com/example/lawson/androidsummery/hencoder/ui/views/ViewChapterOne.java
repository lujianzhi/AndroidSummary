package com.example.lawson.androidsummery.hencoder.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ian.Lu on 2017/9/26.
 * Project : AndroidSummary
 */

public class ViewChapterOne extends View {

    private Paint paint;
    private Paint arcPaint;
    private Path path;

    public ViewChapterOne(Context context) {
        super(context);
        init(context);
    }

    public ViewChapterOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewChapterOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.SQUARE);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.BLUE);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(2);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        drawPoints(canvas);

        drawLines(canvas);

        drawArc(canvas);

        circlePath();
        drawPath(canvas);

        linePath();
        drawPath(canvas);
    }

    private void linePath() {
        //将起始点移动到(425,0)
        path.moveTo(450, 50);
        path.lineTo(480, 100);
        path.rLineTo(100, 0);
        paint.setStyle(Paint.Style.STROKE);
    }

    private void circlePath() {
        path.addCircle(350, 100, 40, Path.Direction.CCW);
        Path circleTwoPath = new Path();
        circleTwoPath.addCircle(400, 100, 50, Path.Direction.CCW);
        path.addPath(circleTwoPath);
    }

    private void drawPath(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawArc(Canvas canvas) {
        //startAngle为正数时，所绘制的弧形在y轴正方向，反之在y轴负方向
        //startAngle为正数时，sweepAngle往x轴负方向绘制，反之在x轴正方向
        canvas.drawArc(90, 10, 300, 100, 135, 45, true, paint);
        canvas.drawArc(90, 10, 300, 100, 90, 45, false, arcPaint);
        canvas.drawArc(90, 10, 300, 100, -90, 90, false, paint);
    }

    private float[] linePoints = new float[]{10, 100, 80, 100, 10, 200, 80, 200, 40, 100, 40, 200};

    private void drawLines(Canvas canvas) {
        canvas.drawLines(linePoints, paint);
    }

    private float[] points = new float[]{10, 10, 80, 10, 80, 80, 10, 80};

    private void drawPoints(Canvas canvas) {
        canvas.drawPoints(points, paint);
    }
}
