package com.example.lawson.androidsummery.picviry;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.getui.gysdk.GYResponse;
import com.getui.gysdk.GyMessageReceiver;

/**
 * Project IanDemo
 * Date 19-5-2
 *
 * @author ian
 */
public class GYReceiver extends GyMessageReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Context context, GYResponse gyResponse) {
        super.onError(context, gyResponse);
        Toast.makeText(context, "onError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGyUidReceived(Context context, String s) {
        super.onGyUidReceived(context, s);
        Toast.makeText(context, "onGyUidReceived", Toast.LENGTH_SHORT).show();
    }
}
