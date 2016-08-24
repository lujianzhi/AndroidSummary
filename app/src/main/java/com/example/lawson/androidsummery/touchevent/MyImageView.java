package com.example.lawson.androidsummery.touchevent;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ImageView;

/***
 * Created by Lawson on 2016/8/24.
 */
public class MyImageView extends ImageView {

    public MyImageView(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
