package com.example.lawson.androidsummery.scroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by Ian.Lu on 2017/4/11.
 * Project : AndroidSummary
 */

public class MyScrollerView extends ImageView {

    public MyScrollerView(Context context) {
        super(context);
        init();
    }

    public MyScrollerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private Scroller scroller;

    private void init() {
        scroller = new Scroller(getContext());
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;
        scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 3000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
