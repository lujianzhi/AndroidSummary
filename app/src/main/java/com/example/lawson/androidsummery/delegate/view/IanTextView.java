package com.example.lawson.androidsummery.delegate.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.example.lawson.androidsummery.delegate.ClickHelper;

/**
 * Created by Ian.Lu on 2018/9/18.
 * Project : AndroidSummary
 */
@SuppressLint("AppCompatCustomView")
public class IanTextView extends TextView {
    public IanTextView(Context context) {
        super(context);
    }

    public IanTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setText("被我代理了");
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean performClick() {
        boolean b = super.performClick();
        if (b) {
            ClickHelper.click(this);
        }
        return b;
    }
}
