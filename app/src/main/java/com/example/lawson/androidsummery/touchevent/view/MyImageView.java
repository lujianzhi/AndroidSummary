package com.example.lawson.androidsummery.touchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MyImageView extends ImageView {

    private final String TAG = "Ian";
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
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
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
