package com.example.lawson.androidsummery.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/7/18.
 * Project : AndroidSummary
 */

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    //divider的绘制样式
    public static int VERTICAL_DIVIDER = 1;
    public static int HORIZONTAL_DIVIDER = 2;

    private Drawable horizontalDivider;
    private Drawable verticalDivider;
    //记录divider的绘制样式
    private int orientation;

    public MyDividerItemDecoration(Context context, int orientation) {
        horizontalDivider = ContextCompat.getDrawable(context, R.drawable.shape_horizontal_divider);
        verticalDivider = ContextCompat.getDrawable(context, R.drawable.shape_vertical_divider);
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != VERTICAL_DIVIDER && orientation != HORIZONTAL_DIVIDER) {
            throw new IllegalArgumentException("输入正确的orientation值");
        }
        this.orientation = orientation;
    }

    TextView header;
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (header == null) {
            header = new TextView(parent.getContext());
            header.setText("I am DrawOver!");
            header.setBackgroundResource(R.color.colorAccent);
            header.setTextColor(0xFFFFFFFF);

            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.UNSPECIFIED);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);
            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(), parent.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(), parent.getLayoutParams().height);

            header.measure(childWidth, childHeight);
            //不执行layout的话，不知道画在哪里
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        }
        c.save();
        c.translate(10, 10);
        header.draw(c);
        c.restore();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == VERTICAL_DIVIDER) {
            drawVertical(c, parent);
        } else if (orientation == HORIZONTAL_DIVIDER) {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (orientation == VERTICAL_DIVIDER) {
            //类似于padding，itemView会有指定数值的padding效果
            outRect.set(10, 20, verticalDivider.getIntrinsicWidth(), 30);
        } else if (orientation == HORIZONTAL_DIVIDER) {
            //类似于padding，itemView会有指定数值的padding效果
            outRect.set(10, 20, 30, horizontalDivider.getIntrinsicHeight());
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //水平样式的话，top和bottom是需要通过childView来计算的
        //而left和right，则一般通过RecyclerView来控制
        //获取水平样式divider的left和right坐标：就是它需要画出来的长度
        int left = parent.getPaddingLeft();
        int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            //getBottom()获取的是，view在父容器中最顶端到最底端的一个高度
            int top = childView.getBottom() + layoutParams.bottomMargin;
            //getIntrinsicHeight()会返回我们horizontalDivider的dp单位高度
            int bottom = top + horizontalDivider.getIntrinsicHeight();
            //设置horizontalDivider的边界
            horizontalDivider.setBounds(left, top, right, bottom);
            //绘制
            horizontalDivider.draw(c);
            Log.i("Ian", "left : " + left + " ; top :" + top + " ; right : " + right + " ; bottom : " + bottom);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getRight() + layoutParams.rightMargin;
            int right = left + verticalDivider.getIntrinsicWidth();
            verticalDivider.setBounds(left, top, right, bottom);
            verticalDivider.draw(c);
        }
    }
}
