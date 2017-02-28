package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ian.Lu on 2016/12/2.
 * Project : AndroidSummary
 */

public class ThirdView extends View {

    private String text = "ap你哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
    //    private String text = "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十";
    private Paint mPaint;
    private Paint.FontMetrics fontMetrics;

//    private TextPaint textPaint;
//    private StaticLayout staticLayout;

    TextView textView;

    public ThirdView(Context context) {
        super(context);
        init();
    }

    public ThirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThirdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.GREEN);
        fontMetrics = mPaint.getFontMetrics();

        Log.i("Ian", "breakText : " + mPaint.breakText(text, 0, 10, true, 30F, null));
        Log.i("Ian", "getFontMetrics : " + mPaint.getFontMetrics(fontMetrics));
        Log.i("Ian", "getFontMetricsInt : " + mPaint.getFontMetricsInt());
        Log.i("Ian", "getFontSpacing : " + mPaint.getFontSpacing());
        mPaint.setUnderlineText(true);

//        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
//        mPaint.setTypeface(Typeface.create(DEFAULT_BOLD, Typeface.ITALIC));
//        mPaint.setTypeface(Typeface.create("DEFAULT_BOLD", Typeface.ITALIC));
//        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font.ttf"));

//        mPaint.setTextSkewX(-0.8f);
//        mPaint.setTextScaleX(2);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.setSubpixelText(true);
//        mPaint.setStrikeThruText(true);
//        mPaint.setLinearText(true);
//        mPaint.setFakeBoldText(true);
//        mPaint.setDither(true);

        mPaint.setShadowLayer(20,5,5,Color.GREEN);
        setLayerType(LAYER_TYPE_SOFTWARE, null);


        Log.i("Ian", "-------------------------");

        Log.i("Ian", "ascent" + fontMetrics.ascent);
        Log.i("Ian", "top" + fontMetrics.top);
        Log.i("Ian", "leading" + fontMetrics.leading);
        Log.i("Ian", "descent" + fontMetrics.descent);
        Log.i("Ian", "bottom" + fontMetrics.bottom);

//        textView = new TextView(getContext());
//        TextPaint textPaint = textView.getPaint();
//        textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font.ttf"));


//        textPaint = new TextPaint();
//        textPaint.setTextSize(50);
//        textPaint.setColor(Color.GREEN);
//        textPaint.baselineShift = 100;
//        textPaint.bgColor = Color.BLUE;
//        textPaint.density = 20;
//        textPaint.linkColor = Color.DKGRAY;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText(text, canvas.getWidth() / 2 - mPaint.measureText(text) / 2, (canvas.getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2), mPaint);
//        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, new Paint());

//        canvas.save();
//        if (staticLayout == null) {
//            staticLayout = new StaticLayout(text, textPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
//        }
//        staticLayout.draw(canvas);
//        canvas.restore();

//        canvas.drawText(text, canvas.getWidth() / 2, (canvas.getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2), mPaint);
//        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, new Paint());

        canvas.drawRect(100, 100, 300, 300, mPaint);
    }
}
