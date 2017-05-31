package com.example.lawson.androidsummery.animation.propertyanimation;

import android.view.View;

/**
 * Created by Ian.Lu on 2017/5/27.
 * Project : AndroidSummary
 */

/**
 * 包装类
 */
public class ViewWrapper {

    private View targetView;

    public ViewWrapper(View targetView) {
        this.targetView = targetView;
    }

    public int getAsd() {
        return targetView.getLayoutParams().width;
    }

    public void setAsd(int width) {
        targetView.getLayoutParams().width = width;
        targetView.requestLayout();
    }
}
