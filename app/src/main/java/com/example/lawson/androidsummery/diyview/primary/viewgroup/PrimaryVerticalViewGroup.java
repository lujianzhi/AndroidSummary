package com.example.lawson.androidsummery.diyview.primary.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ian.Lu on 2017/8/24.
 * Project : AndroidSummary
 */

public class PrimaryVerticalViewGroup extends ViewGroup {

    private int defaultValue = 200;

    public PrimaryVerticalViewGroup(Context context) {
        super(context);
    }

    public PrimaryVerticalViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrimaryVerticalViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 必须要测量子View的宽高，否则屏幕上显示不出子view
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // 设置wrap_content时的默认值。自定义View时也需要做这一步。
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultValue, defaultValue);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultValue, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, defaultValue);
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 因为是实现垂直分布，所以我需要在每次单个的子View绘制完之后，知道下一个子View的初始高度位置
        int childTop = 0;
        // 获取一共有几个子View
        int childrenCount = getChildCount();

        for (int i = 0; i < childrenCount; i++) {
            // 取出子View
            View childView = getChildAt(i);

            // 这里需要注意的是，如果要在自定义的ViewGroup用到MarginLayoutParams，
            // 需要重写public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)方法
            MarginLayoutParams marginLayoutParams = (PrimaryVerticalViewGroup.LayoutParams) childView.getLayoutParams();

            //左上角x坐标
            int ll = marginLayoutParams.leftMargin;
            //左上角y坐标，需要考虑View的初始高度位置
            int lt = childTop + marginLayoutParams.topMargin;
            //右下角x坐标，在ll基础上直接加上View的宽度即可
            int lr = ll + childView.getMeasuredWidth();
            //右下角y坐标，在lt基础上直接加上View的高度即可
            int lb = lt + childView.getMeasuredHeight();
            //因为padding属性，在View内部会进行处理，所以在ViewGroup中，只处理Margin即可
            childView.layout(ll, lt, lr, lb);
            //这里就需要累加，让下一个子View知道它的初始高度位置在哪
            //这个值我来回调了很久，最后发现，用当前子View的高度加上上下的Margin值进行累加即可，
            // 因为padding不需要考虑，而且这个值是累加的，那么每一次layout完一个子View，将它的这三个数值相加，就可以当做下一个子View的初始高度位置
            childTop += childView.getMeasuredHeight() + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
        }
    }

    /**
     * onLayout()中需要用到MarginLayoutParams就需要重写该方法
     *
     * @return 自己的MarginLayoutParams
     */
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PrimaryVerticalViewGroup.LayoutParams(getContext(), attrs);
    }

    /**
     * 创建内部静态类去继承MarginLayoutParams
     */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }
    }
}
