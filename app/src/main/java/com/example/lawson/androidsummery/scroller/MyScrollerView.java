package com.example.lawson.androidsummery.scroller;

import android.content.Context;
import android.databinding.tool.util.L;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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

    /**
     * 需要将View移动到左上角为坐标(destX,destY)的地方
     */
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
            // 调用scrollTo(x,y)方法，改变了View的mScrollX和mScrollY值，
            // 而不是translationX和translationY
            Log.i("Ian", "getScrollX:" + getScrollX() + " ; getScrollY:" + getScrollY());
            postInvalidate();
        }
    }
}
