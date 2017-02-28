package com.example.lawson.androidsummery.diyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ian.Lu on 2016/12/27.
 * Project : AndroidSummary
 */

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();

        int childCount = getChildCount();

        if (childCount > 0) {
            int temp = 0;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);

                child.layout(parentPaddingLeft, parentPaddingTop + temp, child.getMeasuredWidth() + parentPaddingLeft, child.getMeasuredHeight() + temp + parentPaddingTop);
                temp += child.getMeasuredHeight();
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 生成布局参数
     * 从属性配置中生成我们的布局参数
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }

    /**
     * 生成布局参数
     * 将布局参数包装成我们的
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MyLayoutParams(p);
    }

    /**
     * 生成默认的布局参数
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 检查当前布局参数是否是我们定义的类型这在code声明布局参数时常常用到
     */
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MyLayoutParams;
    }

    public static class MyLayoutParams extends MarginLayoutParams {

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public MyLayoutParams(int width, int height) {
            super(width, height);
        }

        public MyLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public MyLayoutParams(LayoutParams source) {
            super(source);
        }
    }

}
