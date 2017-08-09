package com.example.lawson.androidsummery.diyview.learn2draw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ian.Lu on 2017/8/9.
 * Project : AndroidSummary
 */

public class ChangeLineViewGroup extends ViewGroup {

    private int parentWidth;
    private int totaleft = 0;
    private int totalTop = 0;
    private int margin = 10;
    private int maxChildHeight = 0;
    private int totalRight = 0;


    public ChangeLineViewGroup(Context context) {
        super(context);
    }

    public ChangeLineViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeLineViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /* 测量父布局 */
        parentWidth = measureSize(widthMeasureSpec, DisplayUtil.dip2px(getContext(), 240));

        int count = getChildCount();
        int tempMaxChildHeight = 0;
        int tempTotalHeight = 0;
        int tempTotalRight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {

                /* 测量子布局 */
                if (child.getMeasuredWidth() > parentWidth) {
                    child.measure(parentWidth, child.getMeasuredHeight());
                } else
                    child.measure(child.getMeasuredWidth(), child.getMeasuredHeight());

                tempMaxChildHeight = Math.max(tempMaxChildHeight, child.getMeasuredHeight());
                tempTotalRight += child.getMeasuredWidth();
                if (tempTotalRight > parentWidth) {
                    tempTotalHeight += tempMaxChildHeight;
                    tempMaxChildHeight = child.getMeasuredHeight();
                    tempTotalRight = child.getMeasuredWidth();
                }
            }
        }

        /* 获取适配子布局后的高度 */
        int parentHeight = tempTotalHeight + tempMaxChildHeight + margin;
        setMeasuredDimension(parentWidth, parentHeight);
    }

    private int measureSize(int measureSpec, int defaultSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result = defaultSize;

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.max(size, result);
        }

        return result;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount();
        int lineViewCount = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {

                if (i != 0) {
                    /* child 的 left 是上个子 view 的宽加上 margin */
                    totaleft += getChildAt(i - 1).getMeasuredWidth() + margin;
                } else {
                    totaleft = 0;
                    totalTop = 0;
                    maxChildHeight = child.getMeasuredHeight();
                }
                /* child 的 right */
                totalRight = totaleft + child.getMeasuredWidth();
                /* 如果 rigth 大于 父布局的宽， 则换行 */
                if (totalRight > parentWidth) {
                    adjustLine(lineViewCount, i); // 调整这一行的子布局的位置
                    lineViewCount = 0;  // 这一行的子 child 的数量充值
                    totalTop += maxChildHeight;
                    totaleft = 0;
                    maxChildHeight = child.getMeasuredHeight();
                    totalRight = child.getMeasuredWidth();
                } else {
                    maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight());
                }

                child.layout(
                        totaleft,
                        totalTop,
                        totalRight,
                        totalTop + child.getMeasuredHeight()
                );

                /* 统计这一行的子view的数量 */
                lineViewCount++;
            }
        }

        /* 调整最后一行子布局的位置 */
        totaleft = totalRight + margin;
        adjustLine(lineViewCount, count);

    }

    /* 调整一行，让这一行的子布局水平居中 */
    private void adjustLine(int lineViewCount, int i) {
        totaleft = (parentWidth - totaleft) / 2;
        int marginTop;
        for (int lineViewNumber = lineViewCount; lineViewNumber > 0; lineViewNumber--) {
            View lineViewChild = getChildAt(i - lineViewNumber);
            totalRight = totaleft + lineViewChild.getMeasuredWidth();
            if (lineViewChild.getMeasuredHeight() != maxChildHeight) {
                marginTop = (maxChildHeight - lineViewChild.getMeasuredHeight()) / 2;
            } else {
                marginTop = 0;
            }
            lineViewChild.layout(totaleft, totalTop + marginTop, totalRight, totalTop + marginTop + lineViewChild.getMeasuredHeight());
            totaleft += lineViewChild.getMeasuredWidth() + margin;
        }
    }


//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
//
//        // 计算出所有的childView的宽和高
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//
//        int width = sizeWidth; //使用父视图推荐宽度
//        int height = 0; //需计算
//
//        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.EXACTLY) {
//            //使用父视图推荐高度
//            height = sizeHeight;
//        } else {
//
//            int pWidth = 0; //宽度右边界位置
//            int pHeight = 0; //高度下边界位置
//            int cWidth = 0; //子视图宽度
//            int cHeight = 0; //子视图高度
//            MarginLayoutParams lp = null;// 子视图布局属性
//
//            //根据childView计算的出的宽和高
//            for (int i = 0; i < getChildCount(); i++) {
//                View childView = getChildAt(i);
//                cWidth = childView.getMeasuredWidth();
//                cHeight = childView.getMeasuredHeight();
//                lp = (MarginLayoutParams) childView.getLayoutParams();
//
//                pWidth += cWidth + lp.rightMargin + lp.leftMargin;
//                if (pHeight == 0) {
//                    //界面初始化高度
//                    pHeight = cHeight + lp.topMargin + lp.bottomMargin;
//                }
//                if (pWidth > width) {
//                    pWidth = cWidth + lp.rightMargin + lp.leftMargin;
//                    pHeight += cHeight + lp.topMargin + lp.bottomMargin;
//                }
//            }
//            height = pHeight;
//        }
//
//        //设置计算结果
//        setMeasuredDimension(width, height);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int pWidth = 0; //宽度右边界位置
//        int pHeight = 0; //高度下边界位置
//        int cWidth = 0; //子视图宽度
//        int cHeight = 0; //子视图高度
//        MarginLayoutParams lp = null;// 子视图布局属性
//        int cl = 0, cr = 0, ct = 0, cb = 0;
//
//        //遍历所有childView根据其宽和高
//        for (int i = 0; i < getChildCount(); i++) {
//            View childView = getChildAt(i);
//            cWidth = childView.getMeasuredWidth();
//            cHeight = childView.getMeasuredHeight();
//            lp = (MarginLayoutParams) childView.getLayoutParams();
//
//            pWidth += cWidth + lp.leftMargin + lp.rightMargin;
//            if (pWidth > getWidth()) {
//                pWidth = cWidth + lp.leftMargin + lp.rightMargin;
//                pHeight += cHeight + lp.topMargin + lp.bottomMargin;
//            }
//
//            cl = pWidth - cWidth - lp.rightMargin;
//            cr = pWidth - lp.rightMargin;
//            ct = pHeight + lp.topMargin;
//            cb = pHeight + cHeight + lp.topMargin;
//
//            childView.layout(cl, ct, cr, cb);
//        }
//    }
}
