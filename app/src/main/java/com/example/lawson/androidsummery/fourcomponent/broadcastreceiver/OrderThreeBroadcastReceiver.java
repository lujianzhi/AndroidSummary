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

public class OrderThreeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Ian", "OrderThreeBroadcastReceiver -> onReceive ; Thread:" + Thread.currentThread().getName());
        Bundle data = getResultExtras(true);
        if (data != null) {
            String str = data.getString(OrderOneBroadcastReceiver.MY_STRING_DATA);
            Log.i("Ian", "OrderThreeBroadcastReceiver收到了数据 : " + str);
        }
    }
}
