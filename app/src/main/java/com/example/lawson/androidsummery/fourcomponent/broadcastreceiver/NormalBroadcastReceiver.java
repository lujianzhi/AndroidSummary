package com.example.lawson.androidsummery.fourcomponent.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Ian.Lu on 2017/9/12.
 * Project : AndroidSummary
 */

public class NormalBroadcastReceiver extends BroadcastReceiver {

    public static final String MY_ACTION = "normal";
    public static final String MY_DATA = "data";
    public static final String MY_STRING_DATA = "string_data";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Ian", "NormalBroadcastReceiver -> onReceive ; Thread:" + Thread.currentThread().getName());
        String action = intent.getAction();
        if (MY_ACTION.equals(action)) {
            Bundle data = intent.getBundleExtra(MY_DATA);
            if (data != null) {
                Log.i("Ian", "接受数据 : " + data.getString(MY_STRING_DATA));
            }
        }
    }
}
