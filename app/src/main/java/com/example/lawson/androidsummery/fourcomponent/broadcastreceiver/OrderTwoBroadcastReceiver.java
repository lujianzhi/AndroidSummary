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

public class OrderTwoBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Ian", "OrderTwoBroadcastReceiver -> onReceive ; Thread:" + Thread.currentThread().getName());
        Bundle data = getResultExtras(true);
        if (data != null) {
            String str = data.getString(OrderOneBroadcastReceiver.MY_STRING_DATA);
            Log.i("Ian", "OrderTwoBroadcastReceiver收到了数据 : " + str);
            data.putString(OrderOneBroadcastReceiver.MY_STRING_DATA, "OrderTwoBroadcastReceiver进行修改 : 原始内容 count:3");
            Log.i("Ian", "OrderTwoBroadcastReceiver要停止广播的分发");
            setResultExtras(data);
            //停止发送
            abortBroadcast();
        }
    }
}
