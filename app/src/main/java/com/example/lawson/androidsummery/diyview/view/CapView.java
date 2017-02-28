package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ian.Lu on 2016/12/10.
 * Project : AndroidSummary
 */

public class CapView extends View {

    private int height;
    private int width;
    private int sX, sY;//二阶贝塞尔中点坐标
    private int mainY;//两端的y坐标
    private boolean reverse;
    private boolean reverseY;

    private Paint paint;
    private Path path;

    public CapView(Context context) {
        super(context);
        init();
    }

    public CapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.height = h;
        this.width = w;

        this.mainY = h / 8;
        this.sY = -h / 8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(-width / 4, mainY);

        path.quadTo(sX, sY, width + width / 4, mainY);

        path.lineTo(width + width / 4, height);
        path.lineTo(-width / 4, height);
        path.close();

        canvas.drawPath(path, paint);

        //二阶贝塞尔中点坐标处理
        //x轴
        if (sX > (width + width / 4)) {
            reverse = true;
        } else if (sX < -width / 4) {
            reverse = false;
        }
        sX = reverse ? sX - 20 : sX + 20;

        //y轴
        if (mainY <= height) {
            sY += 1;
            mainY += 1;
        }

        path.reset();
        invalidate();
    }
}
