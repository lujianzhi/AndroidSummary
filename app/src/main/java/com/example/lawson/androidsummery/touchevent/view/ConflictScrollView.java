package com.example.lawson.androidsummery.touchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Ian.Lu on 2017/8/25.
 * Project : AndroidSummary
 */

public class ConflictScrollView extends ScrollView {
    public ConflictScrollView(Context context) {
        super(context);
    }

    public ConflictScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConflictScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;

            case MotionEvent.ACTION_MOVE:
                intercept = getChildAt(0).isSelected();
                break;

            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }

        return intercept;
    }
}
