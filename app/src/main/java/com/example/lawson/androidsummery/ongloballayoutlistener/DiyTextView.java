package com.example.lawson.androidsummery.ongloballayoutlistener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by Ian.Lu on 2016/11/10.
 * Project : AndroidSummary
 */

public class DiyTextView extends TextView {
    public DiyTextView(Context context) {
        super(context);
        init();
    }

    public DiyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("ian", "DiyTextView - getWidth : " + getWidth());
                Log.i("ian", "DiyTextView - getHeight : " + getHeight());
                Log.i("ian", "DiyTextView - getMeasuredWidth : " + getMeasuredWidth());
                Log.i("ian", "DiyTextView - getMeasuredHeight : " + getMeasuredHeight());
            }
        });
    }
}
