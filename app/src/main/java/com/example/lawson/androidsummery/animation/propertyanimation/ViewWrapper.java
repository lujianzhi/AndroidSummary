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
        //当view确定自身已经不再适合现有的区域时，
        //该view本身调用这个方法要求parent view重新调用他的onMeasure onLayout来对重新设置自己位置。
        //特别的当view的layoutparameter发生改变，并且它的值还没能应用到view上，这时候适合调用这个方法。
        targetView.requestLayout();
    }
}
