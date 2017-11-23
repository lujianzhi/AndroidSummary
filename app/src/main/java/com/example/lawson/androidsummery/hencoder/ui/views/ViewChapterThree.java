package com.example.lawson.androidsummery.hencoder.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

/**
 * Created by Ian.Lu on 2017/11/21.
 * Project : AndroidSummary
 */

public class ViewChapterThree extends View {

    private Paint paint;
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint5 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint6 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint7 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint8 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint9 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint10 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint11 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint12 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint13 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint textPaint;
    private String textStr = "Hello,Ian,nice";
    private String textStr2 = "雨骨底条今直沿微写";

    public ViewChapterThree(Context context) {
        super(context);
        init();
    }

    public ViewChapterThree(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewChapterThree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        paint.setTextSize(40);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        drawText(canvas);

        drawTextOnPath(canvas);

        staticLayout(canvas);

        setTypeface(canvas);

        setStrikeThruText(canvas);

        setTextAlign(canvas);

        setTextLocale(canvas);

        getFontSpacing(canvas);

        getFontMetrics(canvas);

        getTextBounds(canvas);

        breakText(canvas);

        getRunAdvance(canvas);

        getOffsetForAdvance(canvas);

        hasGlyph(canvas);
    }

    private void hasGlyph(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            paint13.setTextSize(30);
            paint13.setColor(Color.GREEN);
            String text = "a bd sdfgasdg";
            String text2 = "a";
            String text3 = "\uD83C\uDDE8\uD83C\uDDF3";
            boolean hasGlyph = paint13.hasGlyph(text);
            boolean hasGlyph2 = paint13.hasGlyph(text2);
            boolean hasGlyph3 = paint13.hasGlyph(text3);
            canvas.drawText(text + " ; hasGlyph:" + hasGlyph, 740, 340, paint13);
            canvas.drawText(text2 + " ; hasGlyph:" + hasGlyph2, 740, 370, paint13);
            canvas.drawText(text3 + " ; hasGlyph:" + hasGlyph3, 740, 400, paint13);
        }
    }

    private void getOffsetForAdvance(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String text = "12345 789abcdef \uD83C\uDDE8\uD83C\uDDF3";
            int length = text.length();
            float advance = paint12.getRunAdvance(text, 0, length, 0, length, false, length - 5);
            int offset = paint12.getOffsetForAdvance(text, 0, length, 0, length, false, advance);
            canvas.drawText("最后一行的getOffsetForAdvance : " + offset, 1450, 440, paint12);
        }
    }

    private void getRunAdvance(Canvas canvas) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            String text = "12345 789abcdef \uD83C\uDDE8\uD83C\uDDF3";
            int length = text.length();
            paint12.setTextSize(40);
            paint12.setColor(Color.RED);

            float advance1 = paint12.getRunAdvance(text, 0, length, 0, length, false, length);
            float advance2 = paint12.getRunAdvance(text, 0, length, 0, length, false, length - 1);
            float advance3 = paint12.getRunAdvance(text, 0, length, 0, length, false, length - 2);
            float advance4 = paint12.getRunAdvance(text, 0, length, 0, length, false, length - 3);
            float advance5 = paint12.getRunAdvance(text, 0, length, 0, length, false, length - 4);
            float advance6 = paint12.getRunAdvance(text, 0, length, 0, length, false, length - 5);

            canvas.drawText(text, 1450, 200, paint12);
            canvas.drawLine(1450 + advance1, 200 - 40, 1450 + advance1, 200, paint12);
            canvas.drawText(text, 1450, 240, paint12);
            canvas.drawLine(1450 + advance2, 240 - 40, 1450 + advance2, 240, paint12);
            canvas.drawText(text, 1450, 280, paint12);
            canvas.drawLine(1450 + advance3, 280 - 40, 1450 + advance3, 280, paint12);
            canvas.drawText(text, 1450, 320, paint12);
            canvas.drawLine(1450 + advance4, 320 - 40, 1450 + advance4, 320, paint12);
            canvas.drawText(text, 1450, 360, paint12);
            canvas.drawLine(1450 + advance5, 360 - 40, 1450 + advance5, 360, paint12);
            canvas.drawText(text, 1450, 400, paint12);
            canvas.drawLine(1450 + advance6, 400 - 40, 1450 + advance6, 400, paint12);
        }
    }

    private void breakText(Canvas canvas) {
        paint11.setTextSize(40);
        paint11.setColor(Color.RED);
        paint11.setStyle(Paint.Style.FILL);

        float[] measuredWidth = new float[]{0};
        int count = paint11.breakText(textStr, 0, textStr.length(), true, 100, measuredWidth);
        //截取指定长度字符串
        canvas.drawText(textStr.substring(0, count), 1150, 370, paint11);
        canvas.drawText("截取字数:" + count + ", 截取宽度:" + measuredWidth[0], 1150, 390, paint7);

    }

    private void getTextBounds(Canvas canvas) {
        paint9.setTextSize(40);
        paint9.setColor(Color.GREEN);
        paint9.setStyle(Paint.Style.FILL);
        paint10.setStrokeWidth(1);
        paint10.setStyle(Paint.Style.STROKE);
        paint10.setColor(Color.RED);

        canvas.drawText(textStr, 1150, 320, paint9);
        Rect rect = new Rect();
        paint9.getTextBounds(textStr, 0, textStr.length(), rect);
        //将矩阵移动到文字绘制的位置
        rect.left += 1150;
        rect.top += 320;
        rect.right += 1150;
        rect.bottom += 320;
        canvas.drawRect(rect, paint10);
    }

    private void getFontMetrics(Canvas canvas) {
        paint8.setStrokeWidth(1);
        paint8.setTextSize(15);
        paint8.setStyle(Paint.Style.FILL);
        paint8.setTextScaleX(2);
        paint7.setTextSize(80);
        paint7.setColor(Color.BLACK);

        float x = 1150;
        float baseLine = 130;
        float textLength = paint7.measureText(textStr);

        canvas.drawText(textStr, x, baseLine, paint7);
        Paint.FontMetrics fontMetrics = paint7.getFontMetrics();
        //BaseLine
        paint8.setColor(Color.BLACK);
        canvas.drawLine(x, baseLine, x + textLength, baseLine, paint8);
        canvas.drawText("BaseLine", x + textLength, baseLine, paint8);
        //ascent
        paint8.setColor(Color.GREEN);
        canvas.drawLine(x, baseLine + fontMetrics.ascent, x + textLength, baseLine + fontMetrics.ascent, paint8);
        canvas.drawText("ascent", x + textLength, baseLine + fontMetrics.ascent, paint8);
        //descent
        paint8.setColor(Color.YELLOW);
        canvas.drawLine(x, baseLine + fontMetrics.descent, x + textLength, baseLine + fontMetrics.descent, paint8);
        canvas.drawText("descent", x + textLength, baseLine + fontMetrics.descent, paint8);
        //top
        paint8.setColor(Color.BLUE);
        canvas.drawLine(x, baseLine + fontMetrics.top, x + textLength, baseLine + fontMetrics.top, paint8);
        canvas.drawText("top", x + textLength, baseLine + fontMetrics.top, paint8);
        //bottom
        paint8.setColor(Color.RED);
        canvas.drawLine(x, baseLine + fontMetrics.bottom, x + textLength, baseLine + fontMetrics.bottom, paint8);
        canvas.drawText("bottom", x + textLength, baseLine + fontMetrics.bottom + 10, paint8);

        paint7.setTextSize(20);
        canvas.drawText("ascent:" + fontMetrics.ascent, 1150, baseLine + 50, paint7);
        canvas.drawText("descent:" + fontMetrics.descent, 1150, baseLine + 70, paint7);
        canvas.drawText("top:" + fontMetrics.top, 1150, baseLine + 90, paint7);
        canvas.drawText("bottom:" + fontMetrics.bottom, 1150, baseLine + 110, paint7);
        canvas.drawText("leading:" + fontMetrics.leading, 1150, baseLine + 130, paint7);
    }

    private void getFontSpacing(Canvas canvas) {
        paint6.setTextSize(40);
        paint6.setColor(Color.RED);

        canvas.drawText(textStr, 750, 200, paint6);
        canvas.drawText(textStr, 750, 200 + paint6.getFontSpacing(), paint6);
        canvas.drawText(textStr, 750, 200 + paint6.getFontSpacing() * 2, paint6);
    }

    private void setTextLocale(Canvas canvas) {
        paint5.setTextSize(40);
        paint5.setColor(Color.GREEN);
        paint5.setTextLocale(Locale.CHINA);
        canvas.drawText(textStr2, 750, 40, paint5);
        paint5.setTextLocale(Locale.TAIWAN);
        canvas.drawText(textStr2, 750, 80, paint5);
        paint5.setTextLocale(Locale.JAPAN);
        canvas.drawText(textStr2, 750, 120, paint5);
    }

    private void setTextAlign(Canvas canvas) {
        paint4.setTextSize(40);
        paint4.setColor(Color.RED);

        paint4.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(textStr, 450, 280, paint4);
        paint4.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(textStr, 450, 320, paint4);
        paint4.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(textStr, 450, 360, paint4);
    }

    private void setStrikeThruText(Canvas canvas) {
        paint3.setTextSize(40);
        //加删除线
        paint3.setStrikeThruText(true);
        canvas.drawText(textStr, 450, 200, paint3);

        //不加删除线
        paint3.setStrikeThruText(false);
        canvas.drawText(textStr, 450, 240, paint3);
    }

    private void setTypeface(Canvas canvas) {
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(1);
        paint2.setTextSize(40);
        paint2.setTypeface(Typeface.SERIF);
        canvas.drawText(textStr, 450, 40, paint2);
        paint2.setTypeface(Typeface.SANS_SERIF);
        canvas.drawText(textStr, 450, 80, paint2);
        //使用assets的字体资源
        paint2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font.ttf"));
        canvas.drawText(textStr, 450, 120, paint2);
    }

    private void staticLayout(Canvas canvas) {
        String text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        StaticLayout staticLayout1 = new StaticLayout(text1, textPaint, 280, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        String text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz";
        StaticLayout staticLayout2 = new StaticLayout(text2, textPaint, 280, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);

        canvas.save();
        canvas.translate(10, 60);
        staticLayout1.draw(canvas);
        canvas.translate(0, 100);
        staticLayout2.draw(canvas);
        canvas.restore();
    }

    private void drawTextOnPath(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(250, 20);
        path.rLineTo(50, 60);
        path.rLineTo(60, -40);
        path.rLineTo(70, 80);
        paint.setPathEffect(new CornerPathEffect(40));
        canvas.drawPath(path, paint);
        canvas.drawTextOnPath(textStr, path, 0, 0, paint);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText(textStr, 0, 40, paint);
    }
}
