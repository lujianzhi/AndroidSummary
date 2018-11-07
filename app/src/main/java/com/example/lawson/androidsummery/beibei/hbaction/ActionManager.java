package com.example.lawson.androidsummery.beibei.hbaction;

import android.content.Context;

/**
 * Created by Ian.Lu on 2018/11/6.
 * Project : AndroidSummary
 */
public class ActionManager {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;

        Bundle.loadJson();
    }

    public static Object getAction(String uri) {
        Bundle bundle = Bundle.getSpecialBundle(uri);
        if (bundle != null) {
            return bundle.runAction();
        }
        return null;
    }
}
