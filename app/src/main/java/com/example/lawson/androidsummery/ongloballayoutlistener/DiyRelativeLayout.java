package com.example.lawson.androidsummery.ongloballayoutlistener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * Created by Ian.Lu on 2016/11/10.
 * Project : AndroidSummary
 */

public class DiyRelativeLayout extends RelativeLayout {
    public DiyRelativeLayout(Context context) {
        super(context);
        init();
    }

    public DiyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("ian", "DiyRelativeLayout - getWidth : " + getWidth());
                Log.i("ian", "DiyRelativeLayout - getHeight : " + getHeight());
                Log.i("ian", "DiyRelativeLayout - getMeasuredWidth : " + getMeasuredWidth());
                Log.i("ian", "DiyRelativeLayout - getMeasuredHeight : " + getMeasuredHeight());
            }
        });
    }
}
