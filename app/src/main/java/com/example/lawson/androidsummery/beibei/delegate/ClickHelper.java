package com.example.lawson.androidsummery.beibei.delegate;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Ian.Lu on 2018/9/18.
 * Project : AndroidSummary
 */
public class ClickHelper {

    public static final int CLICK = 0x7f0e003b;

    public static void click(View view) {
        String tagData = (String) view.getTag(CLICK);
        Toast.makeText(view.getContext(), "打点数据 : " + tagData, Toast.LENGTH_SHORT).show();
    }
}
