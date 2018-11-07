package com.example.lawson.androidsummery.beibei.delegate.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

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
        //点击监听必须设置，你都不能点，怎么去performClick()？
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
