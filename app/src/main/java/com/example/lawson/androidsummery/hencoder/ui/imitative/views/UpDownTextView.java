package com.example.lawson.androidsummery.hencoder.ui.imitative.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.lawson.androidsummery.hencoder.ui.utils.LogUtils;

/**
 * 按钮递增递减的数字TextView
 * Created by Ian.Lu on 2017/12/5.
 * Project : AndroidSummary
 */

public class UpDownTextView extends View {

    private TextPaint textPaint;

    //改变后的完整数字
    private int originalNumber = 123456;

    //不变的数字坐标
    private float[] unchangedPosition;
    //不变的数字
    private char[] unchangedChars;

    //改变的新数字坐标
    private float[] newPosition;
    //改变的新数字
    private char[] newChars;

    //改变的旧数字坐标
    private float[] oldPosition;
    //改变的旧数字
    private char[] oldChars;

    //文字起始坐标值
    private float startX;
    private float startY;
    //原始数字长度
    private float originalNumberWidth;
    //动画在y方向的距离
    private float originalNumberHeight = 30;
    //动画时长
    private long animDuration = 500;
    //数字变大还是变小
    private boolean isBigger;

    public UpDownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(originalNumberHeight);
        textPaint.setStyle(Paint.Style.STROKE);

        unchangedChars = String.valueOf(originalNumber).toCharArray();
        newChars = new char[]{};
        oldChars = new char[]{};
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = 0;
        originalNumberWidth = textPaint.measureText(String.valueOf(originalNumber));
        startY = getHeight() - (getHeight() - originalNumberHeight) / 2;
        unchangedPosition = new float[]{startX, startY};
        newPosition = new float[]{startX, startY};
        oldPosition = new float[]{startX, startY};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //不变的数字
        canvas.drawText(unchangedChars, 0, unchangedChars.length, unchangedPosition[0], unchangedPosition[1], textPaint);
        //改变的新数字
        canvas.drawText(newChars, 0, newChars.length, newPosition[0], newPosition[1], textPaint);
        //改变的旧数字
        canvas.drawText(oldChars, 0, oldChars.length, oldPosition[0], oldPosition[1], textPaint);

    }

    /**
     * 执行动画系统调用的方法
     */
    public void setChangeNumberY(float changeNumberY) {
        generateNumberPositionData(changeNumberY);
        invalidate();
    }

    /**
     * 给数字的坐标赋值
     */
    private void generateNumberPositionData(float changeNumberY) {

        float multiple = textPaint.measureText(String.valueOf(originalNumber)) / unchangedChars.length;
        float unchangedWidth = newChars.length * multiple;

        newPosition[0] = originalNumberWidth - unchangedWidth;
        oldPosition[0] = originalNumberWidth - unchangedWidth;

        float offset = 0;
        if (isBigger) {
            offset = changeNumberY - originalNumberHeight;
        } else {
            offset = originalNumberHeight + offset;
        }

        newPosition[1] = originalNumberWidth - offset;
        oldPosition[1] = originalNumberWidth - changeNumberY;

    }

    /**
     * 外部调用增加或减少数字
     */
    public void upOrDownNumber(int cell) {
        if (cell == 0) {
            return;
        }
        isBigger = cell > 0;

        generateNumberCharsData(cell);

        originalNumber += cell;

        startAnim(cell);
    }

    /**
     * 开启动画
     */
    private void startAnim(int cell) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "changeNumberY",
                0, cell > 0 ? originalNumberHeight : -originalNumberHeight);
        objectAnimator.setDuration(animDuration).start();
    }

    /**
     * 给数字的字符数组赋值
     */
    private void generateNumberCharsData(int cell) {
        String originalNumberStr = String.valueOf(originalNumber);
        String changedNumberStr = String.valueOf(originalNumber + cell);

        for (int i = 0; i < originalNumberStr.length(); i++) {
            char original = originalNumberStr.charAt(i);
            char changed = changedNumberStr.charAt(i);
            if (original != changed) {
                unchangedChars = originalNumberStr.substring(0, i).toCharArray();
                newChars = changedNumberStr.substring(i).toCharArray();
                oldChars = originalNumberStr.substring(i).toCharArray();
                LogUtils.logCharsArray("unchangedChars", unchangedChars);
                LogUtils.logCharsArray("newChars", newChars);
                LogUtils.logCharsArray("oldChars", oldChars);
                break;
            }
        }

    }

}
