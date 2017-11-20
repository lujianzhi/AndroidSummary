package com.example.lawson.androidsummery.hencoder.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposePathEffect;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/11/13.
 * Project : AndroidSummary
 */

public class ViewChapterTwo extends View {

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Paint paint5;
    private Paint paint6;
    private Paint paint7;
    private Paint paint8;
    private Paint paint9;
    private Paint paint10;
    private Paint paint11;
    private Paint paint12;
    private TextPaint textPaint;

    public ViewChapterTwo(Context context) {
        super(context);
        init();
    }

    public ViewChapterTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewChapterTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint5 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint6 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint7 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint8 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint9 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint9.setColor(Color.RED);
        paint10 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint11 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint12 = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(100);
        textPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        linearGradient(canvas);

        radialGradient(canvas);

        sweepGradient(canvas);

        bitmapShader(canvas);

        composeShader(canvas);

        lightingColorFilter(canvas);

        porterDuffColorFilter(canvas);

        colorMatrixColorFilter(canvas);

        testXfermode(canvas);

        strokeCap(canvas);

        strokeJoin(canvas);

        strokeMiter(canvas);

        cornerPathEffect(canvas);

        discretePathEffect(canvas);

        dashPathEffect(canvas);

        pathDashPathEffect(canvas);

        sumPathEffect(canvas);

        composePathEffect(canvas);

        setShadowLayer(canvas);

        blurMaskFilter(canvas);

        embossMaskFilter(canvas);

        getFillPath(canvas);

        getTextPath(canvas);
    }

    private void getTextPath(Canvas canvas) {
        paint12.reset();
        paint12.setStrokeWidth(1);
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);

        String text = "Ian";
        canvas.drawText(text, 1720, 600, textPaint);

        canvas.save();
        canvas.translate(0, 100);
        Path path = new Path();
        textPaint.getTextPath(text, 0, text.length(), 1720, 600, path);
        canvas.drawPath(path, paint12);
        canvas.restore();
    }

    private void getFillPath(Canvas canvas) {
        paint11.reset();
        paint11.setStyle(Paint.Style.STROKE);
        paint11.setStrokeWidth(1);

        paint12.reset();
        paint12.setStrokeWidth(5);
        paint12.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(1750, 300);
        path.rLineTo(10, 40);
        path.rLineTo(20, -30);
        path.rLineTo(30, 60);
        canvas.drawPath(path, paint12);

        canvas.save();
        //移动画布
        canvas.translate(0, 100);
        Path dstPath = new Path();
        paint12.getFillPath(path, dstPath);
        canvas.drawPath(dstPath, paint11);
        canvas.restore();

    }

    private void embossMaskFilter(Canvas canvas) {
        paint12.reset();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        paint12.setMaskFilter(new EmbossMaskFilter(new float[]{2, 1, 1}, 0.2F, 8, 10));
        canvas.drawBitmap(bitmap, 1240, 640, paint12);
    }

    private void blurMaskFilter(Canvas canvas) {
        paint12.reset();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        paint12.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(bitmap, 0, 640, paint12);
        paint12.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.INNER));
        canvas.drawBitmap(bitmap, 340, 640, paint12);
        paint12.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));
        canvas.drawBitmap(bitmap, 640, 640, paint12);
        paint12.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));
        canvas.drawBitmap(bitmap, 940, 640, paint12);
    }

    private void setShadowLayer(Canvas canvas) {
        paint12.reset();
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);
        paint12.setStrokeWidth(1);

        //在硬件加速开启的情况下， setShadowLayer() 只支持文字的绘制，文字之外的绘制必须关闭硬件加速才能正常绘制阴影。
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        paint12.setShadowLayer(1, 5, 5, Color.BLUE);
        canvas.drawCircle(1750, 200, 20, paint12);
        //清除阴影层
        paint12.clearShadowLayer();
    }

    private void composePathEffect(Canvas canvas) {
        paint12.reset();
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);
        paint12.setStrokeWidth(1);

        PathEffect dashPathEffect = new DashPathEffect(new float[]{20, 5}, 0);
        PathEffect discretePathEffect = new DiscretePathEffect(10, 5);

        PathEffect composePathEffect = new ComposePathEffect(discretePathEffect, dashPathEffect);
        Path path = new Path();
        path.moveTo(1750, 100);
        path.rLineTo(10, 40);
        path.rLineTo(20, -30);
        path.rLineTo(30, 60);
        paint12.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint12);
    }

    private void sumPathEffect(Canvas canvas) {
        paint12.reset();
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);
        paint12.setStrokeWidth(1);

        PathEffect dashPathEffect = new DashPathEffect(new float[]{20, 5}, 0);
        PathEffect discretePathEffect = new DiscretePathEffect(10, 5);

        PathEffect sumPathEffect = new SumPathEffect(dashPathEffect, discretePathEffect);
        Path path = new Path();
        path.moveTo(1750, 20);
        path.rLineTo(10, 40);
        path.rLineTo(20, -30);
        path.rLineTo(30, 60);
        paint12.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint12);
    }

    private void pathDashPathEffect(Canvas canvas) {
        paint12.reset();
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);
        paint12.setStrokeWidth(4);

        Path shapePath = new Path();
        shapePath.moveTo(10, 0);
        shapePath.lineTo(0, 20);
        shapePath.lineTo(20, 20);
        shapePath.close();
        PathEffect pathDashPathEffect = new PathDashPathEffect(shapePath, 30, 0, PathDashPathEffect.Style.TRANSLATE);
        paint12.setPathEffect(pathDashPathEffect);
        canvas.drawRect(1500, 20, 1700, 220, paint12);
        PathEffect pathDashPathEffect2 = new PathDashPathEffect(shapePath, 30, 0, PathDashPathEffect.Style.MORPH);
        paint12.setPathEffect(pathDashPathEffect2);
        canvas.drawRect(1500, 240, 1700, 440, paint12);
        PathEffect pathDashPathEffect3 = new PathDashPathEffect(shapePath, 30, 0, PathDashPathEffect.Style.ROTATE);
        paint12.setPathEffect(pathDashPathEffect3);
        canvas.drawRect(1500, 460, 1700, 660, paint12);
    }

    private void dashPathEffect(Canvas canvas) {
        paint12.reset();
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);
        paint12.setStrokeWidth(4);
        PathEffect dashPathEffect = new DashPathEffect(new float[]{5, 2, 15, 5}, 0);

        Path path = new Path();
        path.moveTo(1400, 260);
        path.rLineTo(10, 40);
        path.rLineTo(20, -30);
        path.rLineTo(30, 60);
        paint12.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint12);

        Path path2 = new Path();
        path2.moveTo(1400, 300);
        path2.rLineTo(10, 40);
        path2.rLineTo(20, -30);
        path2.rLineTo(30, 60);
        paint12.setPathEffect(null);
        canvas.drawPath(path2, paint12);
    }

    private void discretePathEffect(Canvas canvas) {
        paint12.reset();
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);
        paint12.setStrokeWidth(4);

        //构造函数参数：segmentLength 是用来拼接的每个线段的长度(越小越密集)， deviation 是偏离量。
        PathEffect discretePathEffect = new DiscretePathEffect(8, 5);

        Path path = new Path();
        path.moveTo(1400, 140);
        path.rLineTo(10, 40);
        path.rLineTo(20, -30);
        path.rLineTo(30, 60);
        paint12.setPathEffect(discretePathEffect);
        canvas.drawPath(path, paint12);

        Path path2 = new Path();
        path2.moveTo(1400, 180);
        path2.rLineTo(10, 40);
        path2.rLineTo(20, -30);
        path2.rLineTo(30, 60);
        paint12.setPathEffect(null);
        canvas.drawPath(path2, paint12);
    }

    private void cornerPathEffect(Canvas canvas) {
        paint12.reset();
        paint12.setStyle(Paint.Style.STROKE);
        paint12.setColor(Color.RED);
        paint12.setStrokeWidth(4);
        PathEffect pathEffect = new CornerPathEffect(20);
        Path path = new Path();
        path.moveTo(1400, 20);
        path.rLineTo(10, 40);
        path.rLineTo(20, -30);
        path.rLineTo(30, 60);
        //设置CornerPathEffect
        paint12.setPathEffect(pathEffect);
        canvas.drawPath(path, paint12);

        Path path2 = new Path();
        path2.moveTo(1400, 60);
        path2.rLineTo(10, 40);
        path2.rLineTo(20, -30);
        path2.rLineTo(30, 60);
        //不设置CornerPathEffect
        paint12.setPathEffect(null);
        canvas.drawPath(path2, paint12);
    }

    private void strokeMiter(Canvas canvas) {
        paint10.setStrokeWidth(20);
        paint10.setColor(Color.RED);
        paint10.setStyle(Paint.Style.STROKE);
        paint10.setStrokeJoin(Paint.Join.MITER);
        paint10.setStrokeMiter(1);
        Path path = new Path();
        path.moveTo(1300, 20);
        path.lineTo(1350, 20);
        path.rLineTo(-40, 25);
        canvas.drawPath(path, paint10);
        paint11.setStrokeWidth(20);
        paint11.setColor(Color.RED);
        paint11.setStyle(Paint.Style.STROKE);
        paint11.setStrokeJoin(Paint.Join.MITER);
        paint11.setStrokeMiter(4);
        Path path2 = new Path();
        path2.moveTo(1300, 70);
        path2.lineTo(1350, 70);
        path2.rLineTo(-40, 25);
        canvas.drawPath(path2, paint11);
    }

    private void strokeJoin(Canvas canvas) {
        paint10.setStrokeWidth(20);
        paint10.setColor(Color.RED);
        paint10.setStyle(Paint.Style.STROKE);
        paint10.setStrokeJoin(Paint.Join.MITER);
        Path path = new Path();
        path.moveTo(1250, 20);
        path.lineTo(1270, 20);
        path.rLineTo(-20, 40);
        canvas.drawPath(path, paint10);

        paint10.setStrokeJoin(Paint.Join.ROUND);
        path.moveTo(1250, 90);
        path.lineTo(1270, 90);
        path.rLineTo(-20, 50);
        canvas.drawPath(path, paint10);

        paint10.setStrokeJoin(Paint.Join.BEVEL);
        path.moveTo(1250, 170);
        path.lineTo(1270, 170);
        path.rLineTo(-20, 50);
        canvas.drawPath(path, paint10);
    }

    private void strokeCap(Canvas canvas) {
        paint10.setStrokeWidth(20);
        paint10.setColor(Color.RED);
        paint10.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(1200, 20, 1220, 20, paint10);
        paint10.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(1200, 80, 1220, 80, paint10);
        paint10.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(1200, 140, 1220, 140, paint10);
    }

    private void testXfermode(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        //没有透明区域导致的PorterDuffXfermode无效
//        canvas.drawRect(900,0,1100,200,paint9);
//        paint9.setXfermode(xfermode);
//        canvas.drawCircle(1100,100,100,paint9);
//        paint9.setXfermode(null);

        Bitmap dragon = BitmapFactory.decodeResource(getResources(), R.drawable.dragon);
        Bitmap circle = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        //使用离屏缓冲
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dragon, 800, 0, paint9);
        paint9.setXfermode(xfermode);
        canvas.drawBitmap(circle, 800, 0, paint9);
        paint9.setXfermode(null);
        //恢复
        canvas.restoreToCount(saved);
    }

    private void colorMatrixColorFilter(Canvas canvas) {
        //只留绿色
        float[] colorMatrixArray = new float[]{
                0, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 0, 0,
                1, 1, 1, 1, 1
        };
        ColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrixArray);
        paint8.setColorFilter(colorMatrixColorFilter);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        canvas.drawBitmap(bitmap, 600, 200, paint8);
    }

    private void porterDuffColorFilter(Canvas canvas) {
        //使红色加深
        ColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
        paint7.setColorFilter(porterDuffColorFilter);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        canvas.drawBitmap(bitmap, 300, 200, paint7);
    }

    private void lightingColorFilter(Canvas canvas) {
        //去掉蓝色
        ColorFilter lightingColorFilter = new LightingColorFilter(0xffff00, 0x000000);
        paint6.setColorFilter(lightingColorFilter);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        canvas.drawBitmap(bitmap, 0, 200, paint6);
    }

    private void composeShader(Canvas canvas) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        Shader bitmapShader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.voice_icon);
        Shader bitmapShader2 = new BitmapShader(bitmap2, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        //需要关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        Shader composeShader = new ComposeShader(bitmapShader1, bitmapShader2, PorterDuff.Mode.DST_OUT);
        paint5.setShader(composeShader);
        canvas.drawRect(0, 0, 200, 200, paint5);
    }

    private void bitmapShader(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        Shader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint4.setShader(bitmapShader);
        canvas.drawCircle(110, 210, 60, paint4);
    }

    private void sweepGradient(Canvas canvas) {
        Shader sweepGradient = new SweepGradient(720, 70, Color.RED, Color.BLACK);
        paint3.setShader(sweepGradient);
        canvas.drawRect(670, 20, 770, 120, paint3);
        canvas.drawText("SweepGradient", 670, 140, textPaint);
    }

    private void radialGradient(Canvas canvas) {
        Shader radialGradient1 = new RadialGradient(390, 70, 20, Color.RED, Color.BLACK, Shader.TileMode.CLAMP);
        paint2.setShader(radialGradient1);
        canvas.drawRect(340, 20, 440, 120, paint2);
        canvas.drawText("CLAMP", 340, 140, textPaint);

        Shader radialGradient2 = new RadialGradient(500, 70, 20, Color.RED, Color.BLACK, Shader.TileMode.MIRROR);
        paint2.setShader(radialGradient2);
        canvas.drawRect(450, 20, 550, 120, paint2);
        canvas.drawText("MIRROR", 450, 140, textPaint);

        Shader radialGradient3 = new RadialGradient(610, 70, 20, Color.RED, Color.BLACK, Shader.TileMode.REPEAT);
        paint2.setShader(radialGradient3);
        canvas.drawRect(560, 20, 660, 120, paint2);
        canvas.drawText("REPEAT", 560, 140, textPaint);
    }

    private void linearGradient(Canvas canvas) {
        Shader linearGradient1 = new LinearGradient(20, 20, 40, 40, Color.BLACK, Color.RED, Shader.TileMode.CLAMP);
        paint1.setShader(linearGradient1);
        canvas.drawRect(20, 20, 120, 120, paint1);
        canvas.drawText("CLAMP", 20, 140, textPaint);

        Shader linearGradient2 = new LinearGradient(130, 20, 150, 40, Color.BLACK, Color.RED, Shader.TileMode.REPEAT);
        paint1.setShader(linearGradient2);
        canvas.drawRect(130, 20, 230, 120, paint1);
        canvas.drawText("REPEAT", 130, 140, textPaint);

        Shader linearGradient3 = new LinearGradient(240, 20, 260, 40, Color.BLACK, Color.RED, Shader.TileMode.MIRROR);
        paint1.setShader(linearGradient3);
        canvas.drawRect(240, 20, 330, 120, paint1);
        canvas.drawText("MIRROR", 240, 140, textPaint);
    }


}
