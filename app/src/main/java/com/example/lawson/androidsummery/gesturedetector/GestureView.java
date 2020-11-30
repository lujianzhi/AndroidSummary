package com.example.lawson.androidsummery.gesturedetector;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Created by Ian.Lu on 2017/4/11.
 * Project : AndroidSummary
 */

public class GestureView extends ImageView {

    private GestureDetector gestureDetector;
    private GestureDetector.OnDoubleTapListener onDoubleTapListener;

    private class MyOnGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("Ian", "onDown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("Ian", "onShowPress");

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("Ian", "onSingleTapUp");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("Ian", "onScroll : distanceX:" + distanceX + ";distanceY:" + distanceY);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("Ian", "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("Ian", "onFling : velocityX:" + velocityX + ";velocityY:" + velocityY);
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("Ian", "onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("Ian", "onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("Ian", "onDoubleTapEvent");
            return true;
        }
    }

    public GestureView(Context context) {
        super(context);
        init();
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), new MyOnGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
