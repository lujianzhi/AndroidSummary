package com.example.lawson.androidsummery.diyview;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Ian.Lu on 2016/12/6.
 * Project : AndroidSummary
 */

public class MeasureUtil {
    public static int[] getScreenSize(Activity mContext) {
        int[] data = new int[2];
        WindowManager manager = mContext.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        data[0] = outMetrics.widthPixels;
        data[1] = outMetrics.heightPixels;
        return data;
    }
}
