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

public class OrderOneBroadcastReceiver extends BroadcastReceiver {
    public static final String MY_ACTION = "order";
    public static final String MY_DATA = "data";
    public static final String MY_STRING_DATA = "string_data";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Ian", "OrderOneBroadcastReceiver -> onReceive ; Thread:" + Thread.currentThread().getName());
        //通过getResultExtras获取Bundle对象
        Bundle data = getResultExtras(true);
        if (data != null) {
            String str = data.getString(MY_STRING_DATA);
            Log.i("Ian", "OrderOneBroadcastReceiver收到了数据 : " + str);
            data.putString(MY_STRING_DATA, "OrderOneBroadcastReceiver进行修改 : 原始内容 count:2");
            //重新设一遍
            setResultExtras(data);
        }
    }
}
