package com.example.lawson.androidsummery.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyImageView extends ImageView {

    private final String TAG = "lawson";
    private final String CURRENT = "MyImageView : ";

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, CURRENT+"dispatchTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT+"dispatchTouchEvent : ACTION_UP");
                break;
        }

        Log.i(TAG,CURRENT+"dispatchTouchEvent : "+super.dispatchTouchEvent(event));
        return super.dispatchTouchEvent(event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, CURRENT+"onTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT+"onTouchEvent : ACTION_UP");
                break;
        }
        Log.i(TAG,CURRENT+"onTouchEvent : "+super.dispatchTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
