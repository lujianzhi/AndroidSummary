package com.example.lawson.androidsummery.hencoder.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/11/23.
 * Project : AndroidSummary
 */

public class ViewChapterFour extends View {

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera camera = new Camera();
    private Camera camera2 = new Camera();

    public ViewChapterFour(Context context) {
        super(context);
    }

    public ViewChapterFour(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewChapterFour(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);

        clipRect(canvas);

        clipPath(canvas);

        rotate(canvas);

        scale(canvas);

        skew(canvas);

        matrixCommon(canvas);

        setPolyToPoly(canvas);

        cameraRotateX(canvas);

        cameraRotateY(canvas);

        cameraRotateZ(canvas);

        setLocation(canvas);
    }

    private void setLocation(Canvas canvas) {
        canvas.save();
        canvas.translate(1200, 850);

        camera2.save();
        //参数单位为inch，默认是(0,0,-8)位置
        camera2.setLocation(0, 0, -20);
        camera2.rotateX(30);
        canvas.translate(bitmap.getWidth() / 2, 0);
        camera2.applyToCanvas(canvas);
        canvas.translate(-bitmap.getWidth() / 2, 0);
        camera2.restore();

        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }

    private void cameraRotateZ(Canvas canvas) {
        canvas.save();
        canvas.translate(800, 850);

        //Camera也需要save
        camera.save();
        //根据图片中X轴的旋转方向，旋转30度
        camera.rotateZ(-30);
        //将画布移动回原来的位置(注意顺序)
        canvas.translate(bitmap.getWidth() / 2, 0);
        //将camera应用到画布上
        camera.applyToCanvas(canvas);
        //将画布移动到靠近坐标系原点位置(注意顺序)
        canvas.translate(-bitmap.getWidth() / 2, 0);
        //Camera也需要restore
        camera.restore();

        canvas.drawBitmap(bitmap, 50, 0, paint);
        canvas.restore();
    }

    private void cameraRotateY(Canvas canvas) {
        canvas.save();
        canvas.translate(400, 850);

        //Camera也需要save
        camera.save();
        //根据图片中X轴的旋转方向，旋转30度
        camera.rotateY(30);
        //将画布移动回原来的位置(注意顺序)
        canvas.translate(bitmap.getWidth() / 2, 0);
        //将camera应用到画布上
        camera.applyToCanvas(canvas);
        //将画布移动到靠近坐标系原点位置(注意顺序)
        canvas.translate(-bitmap.getWidth() / 2, 0);
        //Camera也需要restore
        camera.restore();

        canvas.drawBitmap(bitmap, 50, 0, paint);
        canvas.restore();
    }

    private void cameraRotateX(Canvas canvas) {
        canvas.save();
        canvas.translate(0, 850);

        //Camera也需要save
        camera.save();
        //根据图片中X轴的旋转方向，旋转30度
        camera.rotateX(30);
        //将画布移动回原来的位置(注意顺序)
        canvas.translate(bitmap.getWidth() / 2, 0);
        //将camera应用到画布上
        camera.applyToCanvas(canvas);
        //将画布移动到靠近坐标系原点位置(注意顺序)
        canvas.translate(-bitmap.getWidth() / 2, 0);
        //Camera也需要restore
        camera.restore();

        canvas.drawBitmap(bitmap, 50, 0, paint);
        canvas.restore();
    }

    private void setPolyToPoly(Canvas canvas) {
        float offset = 100;
        //一个点(0,0)平移到(0+offset,0+offset)
        float[] srcPoints1 = new float[]{0, 0};
        float[] dstPoints1 = new float[]{0 + offset, 0 + offset};
        //两个点旋转(选中点和右上角)和缩放(跟旋转道理一样)
        float[] srcPoints2 = new float[]{bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth(), 0};
        float[] dstPoints2 = new float[]{bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth(), bitmap.getHeight()};
        //三个点错切
        float[] srcPoints3 = new float[]{0, 0, 0, bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight()};
        float[] dstPoints3 = new float[]{0, 0, 0 + offset, bitmap.getHeight(), bitmap.getWidth() + offset, bitmap.getHeight()};
        //四个点随便搞(3D效果)
        float[] srcPoints4 = new float[]{0, 0, 0, bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight(), bitmap.getWidth(), 0};
        float[] dstPoints4 = new float[]{0 + offset, 0, 0, bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight(), bitmap.getWidth() - offset, 0};

        Matrix matrix = new Matrix();
        matrix.reset();

        canvas.save();
        canvas.translate(0, 450);
        //一个点，实现平移
        matrix.setPolyToPoly(srcPoints1, 0, dstPoints1, 0, 1);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(400, 450);
        //两个点，实现旋转
        matrix.setPolyToPoly(srcPoints2, 0, dstPoints2, 0, 2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(700, 450);
        //三个点，实现错切
        matrix.setPolyToPoly(srcPoints3, 0, dstPoints3, 0, 3);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(1100, 450);
        //四个点，3D效果
        matrix.setPolyToPoly(srcPoints4, 0, dstPoints4, 0, 4);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }

    private void matrixCommon(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.postTranslate(1600, 0);
        matrix.preRotate(30, 0, 0);

        canvas.save();
        //尽量用concat，而不是setMatrix()
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }

    private void skew(Canvas canvas) {
        canvas.save();
        canvas.translate(1000, 0);
        //错切
        canvas.skew(0.8f, 0.3f);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }

    private void scale(Canvas canvas) {
        canvas.save();
        canvas.translate(800, 100);
        //缩放
        canvas.scale(0.3f, 1.4f, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }

    private void rotate(Canvas canvas) {
        canvas.save();
        //平移
        canvas.translate(700, 0);
        //旋转
        canvas.rotate(50, 0, 0);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }

    private void clipPath(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        path.addCircle(500, 100, 50, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 300, 20, paint);
        canvas.restore();
    }

    private void clipRect(Canvas canvas) {
        //需要保存一下
        canvas.save();
        //裁剪
        canvas.clipRect(20, 20, 120, 120);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        //然后恢复，否则裁剪效果会一直生效
        canvas.restore();
        //原图
        canvas.drawBitmap(bitmap, 130, 20, paint);
    }
}
