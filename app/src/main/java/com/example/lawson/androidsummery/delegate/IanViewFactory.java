package com.example.lawson.androidsummery.delegate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.example.lawson.androidsummery.delegate.view.IanTextView;

/**
 * Created by Ian.Lu on 2018/9/18.
 * Project : AndroidSummary
 */
public class IanViewFactory {

    public static View createView(View parent, String name, Context context, AttributeSet attrs) {
        Log.i("Ian", "View : " + name + " 在这里可以被替换");
        if ("TextView".equals(name)) {
            return new IanTextView(context, attrs);
        }
        return null;
    }
}
