package com.example.lawson.androidsummery.diyview.dragview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Ian.Lu on 2016/11/22.
 * Project : AndroidSummary
 */

public class DemoView extends ImageView {
    private int lastY, lastTop, lastBottom, screenHeight;
    private IActionUp iActionUp;
    private boolean isMoveEffect;

    public DemoView(Context context) {
        super(context);
        init(context);
    }

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
//        setTranslationX(500);
//        setTranslationY(500);
//        Log.i("Ian", "top:" + getTop() + "; left:" + getLeft() + "; right:" + getRight() + "; bottom:" + getBottom());
//        Log.i("Ian", "y:" + getY() + "; x:" + getX());
//        Log.i("Ian", "getTranslationX:" + getTranslationX() + "; getTranslationY:" + getTranslationY());
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取到手指处的横坐标和纵坐标
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                lastTop = getTop();
                lastBottom = getBottom();
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offY = y - lastY;
                //调用layout方法来重新放置它的位置
                int t = getTop() + offY;
                int b = getBottom() + offY;
                if (t < 0) {
                    t = 0;
                    b = t + getHeight();
                }
                Log.i("Ian", "bottom:" + b);
                if (b > screenHeight) {
                    b = screenHeight;
                    t = b - getHeight();
                }
                layout(getLeft(), t, getRight(), b);
                isMoveEffect = Math.abs(lastTop - getTop()) > getHeight();
                Log.i("Ian", "top:" + getTop() + "; left:" + getLeft() + "; right:" + getRight() + "; bottom:" + getBottom());
                Log.i("Ian", "y:" + getY() + "; x:" + getX());
                Log.i("Ian", "getTranslationX:" + getTranslationX() + "; getTranslationY:" + getTranslationY());
                break;
            case MotionEvent.ACTION_UP:
                if (iActionUp != null && isMoveEffect) {
                    iActionUp.actionUp(getTop() - lastTop);
                    isMoveEffect = false;
                    lastTop = 0;
                } else {
                    layout(getLeft(), lastTop, getRight(), lastBottom);
                }
                break;
        }
        return true;
    }

    public void setiActionUp(IActionUp iActionUp) {
        this.iActionUp = iActionUp;
    }

    public interface IActionUp {
        void actionUp(int offY);
    }

}
