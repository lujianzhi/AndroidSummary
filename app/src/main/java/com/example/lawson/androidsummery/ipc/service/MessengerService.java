package com.example.lawson.androidsummery.ipc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.lawson.androidsummery.ipc.activity.messenger.MessengerActivity;

/**
 * 服务端
 */
public class MessengerService extends Service {
    public static final int MSG_FROM_SERVER = 1;
    public static final String REPLY = "reply";

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MessengerActivity.MSG_FROM_CLIENT:
                    //接受客户端的消息
                    Log.i("Ian", "MessengerService : 获取的数据:" + msg.getData().getString(MessengerActivity.DATA));

                    //回复给客户端消息
                    Messenger client = msg.replyTo;
                    Message message = Message.obtain(null, MSG_FROM_SERVER);
                    Bundle data = new Bundle();
                    data.putString(REPLY, "我是服务器，收到了你的消息，我的进程是com.example.lawson.androidsummery.ipc.service:MessengerService");
                    message.setData(data);
                    try {
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
