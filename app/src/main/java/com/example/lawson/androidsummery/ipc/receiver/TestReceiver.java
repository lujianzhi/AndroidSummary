package com.example.lawson.androidsummery.ipc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * USER：lujianzhi
 * DATE：2024/7/29
 */
public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ian", "接受到数据");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String tag = bundle.getString("tag");
            String body = bundle.getString("body");
            Log.i("ian", "收到数据：tag-" + tag + "; body-" + body);
        }
    }
}
