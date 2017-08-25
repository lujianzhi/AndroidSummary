package com.example.lawson.androidsummery.touchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

    private final String TAG = "Ian";
    private final String CURRENT = "MyLinearLayout : ";

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, CURRENT + "onInterceptTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, CURRENT + "onInterceptTouchEvent : ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT + "onInterceptTouchEvent : ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, CURRENT + "onTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, CURRENT + "onTouchEvent : ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT + "onTouchEvent : ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
