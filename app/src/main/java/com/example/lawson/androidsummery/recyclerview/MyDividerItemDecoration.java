package com.example.lawson.androidsummery.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/7/18.
 * Project : AndroidSummary
 */

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    public static int VERTICAL_DIVIDER = 1;
    public static int HORIZONTAL_DIVIDER = 2;

    private Drawable divider;
    private int orientation;

    public MyDividerItemDecoration(Context context, int orientation) {
        divider = context.getResources().getDrawable(R.drawable.shape_divider);
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != VERTICAL_DIVIDER || orientation != HORIZONTAL_DIVIDER) {
            throw new IllegalArgumentException("输入正确的orientation值");
        }
        this.orientation = orientation;
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
            outRect.set(0, 0, 0, divider.getIntrinsicHeight());
        } else if (orientation == HORIZONTAL_DIVIDER) {
            outRect.set(0, 0, divider.getIntrinsicWidth(), 0);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int top = childView.getBottom() + layoutParams.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getRight() + layoutParams.rightMargin;
            int right = left + divider.getIntrinsicWidth();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}
