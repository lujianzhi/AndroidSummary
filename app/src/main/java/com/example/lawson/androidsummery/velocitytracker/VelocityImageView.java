package com.example.lawson.androidsummery.velocitytracker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.ImageView;

/**
 * Created by Ian.Lu on 2017/4/10.
 * Project : AndroidSummary
 */

public class VelocityImageView extends ImageView {

    public VelocityImageView(Context context) {
        super(context);
        init();
    }

    public VelocityImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VelocityImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VelocityImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private VelocityTracker velocityTracker;

    private void init() {
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.computeCurrentVelocity(1000);
                Log.i("Ian", "x: " + velocityTracker.getXVelocity());
                Log.i("Ian", "y: " + velocityTracker.getYVelocity());
                break;
        }
        return true;
    }

    public void clear() {
        velocityTracker.clear();
        velocityTracker.recycle();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear();
    }
}
