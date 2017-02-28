package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ian.Lu on 2016/12/5.
 * Project : AndroidSummary
 */

public class MaskFilterView extends View {

    private Paint paint;

    public MaskFilterView(Context context) {
        super(context);
        init();
    }

    public MaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        paint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(400, 400, 200, paint);
    }
}
