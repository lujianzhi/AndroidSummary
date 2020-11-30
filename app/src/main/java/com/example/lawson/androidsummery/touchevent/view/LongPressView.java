package com.example.lawson.androidsummery.touchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;


/**
 * Created by Ian.Lu on 2017/4/10.
 * Project : AndroidSummary
 */

public class LongPressView extends ImageView {
    public LongPressView(Context context) {
        super(context);
    }

    public LongPressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LongPressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LongPressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private boolean isFirst = true;
    long firstTouchTime = 0;
    double firstPositionY = 0;
    double firstPositionX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isLongClick;
        long lastTouchTime;
        double lastPositionX;
        double lastPositionY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouchTime = event.getEventTime();
                firstPositionX = event.getX();
                firstPositionY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                lastTouchTime = event.getEventTime();
                lastPositionX = event.getX();
                lastPositionY = event.getY();
                Log.i("Ian", "最后一次触碰时间 : " + lastTouchTime);
                isLongClick = dealLongTouchEvent(firstTouchTime, lastTouchTime, firstPositionX, firstPositionY, lastPositionX, lastPositionY);
                if (isLongClick && isFirst) {
                    isFirst = false;
                    Toast.makeText(getContext(), "长按了", Toast.LENGTH_SHORT).show();
                } else {
                    isLongClick = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isFirst = true;
                break;
        }
        return true;
    }

    private boolean dealLongTouchEvent(long firstTouchTime, long lastTouchTime
            , double firstPositionX, double firstPositionY
            , double lastPositionX, double lastPositionY) {
        long intervalTime = lastTouchTime - firstTouchTime;
        double offsetX = Math.abs(firstPositionX - lastPositionX);
        double offsetY = Math.abs(firstPositionY - lastPositionY);
        Log.i("Ian", "间隔时间 : " + intervalTime);
        return intervalTime > 1000 && offsetX <= 10 && offsetY <= 10;
    }

}
