package com.example.lawson.androidsummery.viewvisibility;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Ian.Lu on 2018/5/31.
 * Project : AndroidSummary
 */
public class MyScrollView extends ScrollView {

    private TextView mTextView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextView(TextView textView) {
        mTextView = textView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Rect rect = new Rect();
        getHitRect(rect);
        if (mTextView.getLocalVisibleRect(rect)) {
            Log.i("Ian", mTextView.getText().toString() + " : 可见");
        } else {
            Log.i("Ian", mTextView.getText().toString() + " : 不可见");
        }
    }
}
