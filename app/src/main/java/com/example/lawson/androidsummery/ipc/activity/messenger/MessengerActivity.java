package com.example.lawson.androidsummery.ipc.activity.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.ipc.service.MessengerService;

/**
 * 客户端
 */
public class MessengerActivity extends AppCompatActivity {
    public static final int MSG_FROM_CLIENT = 1;
    public static final String DATA = "data";

    private TextView myProcessNameTv;
    private TextView serviceProcessNameTv;

    //发送给服务器的Messenger
    private Messenger mSendMessenger;
    //响应服务器回复消息的Messenger
    private Messenger mReplyMessenger = new Messenger(new MessengerHandler());
    private String myProcessNameStr;

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVER:
                    serviceProcessNameTv.setText(msg.getData().getString(MessengerService.REPLY));
                    break;
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //客户端发送消息给服务器
            mSendMessenger = new Messenger(service);
            Message mMessage = Message.obtain(null, MSG_FROM_CLIENT);
            //设置reply属性，来获取服务器返回的消息
            mMessage.replyTo = mReplyMessenger;
            //设置客户端需要传给服务器的数据
            Bundle data = new Bundle();
            data.putString(DATA, myProcessNameStr);
            mMessage.setData(data);
            try {
                //发送
                mSendMessenger.send(mMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        myProcessNameTv = findViewById(R.id.my_process_name);
        serviceProcessNameTv = findViewById(R.id.service_process_name);

        //服务的Intent
        Intent intent = new Intent(this, MessengerService.class);
        //绑定服务
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        unbindService(mConnection);
    }
}
