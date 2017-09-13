package com.example.lawson.androidsummery.fourcomponent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fourcomponent.broadcastreceiver.NormalBroadcastReceiver;
import com.example.lawson.androidsummery.fourcomponent.broadcastreceiver.OrderFinalBroadcastReceiver;
import com.example.lawson.androidsummery.fourcomponent.broadcastreceiver.OrderOneBroadcastReceiver;
import com.example.lawson.androidsummery.fourcomponent.service.MyService;

public class FourComponentActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myBinder.saySomething("It's from FourComponentActivity");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_component);

        service();

        broadcastReceiver();
    }

    private void broadcastReceiver() {
        findViewById(R.id.normal_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalBroadcast();
            }
        });

        findViewById(R.id.ordered_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderedBroadcast();
            }
        });
    }

    private void orderedBroadcast() {
        Intent intent = new Intent();
        intent.setAction(OrderOneBroadcastReceiver.MY_ACTION);
        Bundle data = new Bundle();
        data.putString(OrderOneBroadcastReceiver.MY_STRING_DATA, "原始内容 count:1");
        //Bundle对象需要在此方法传入，不能通过intent，否则后续没法修改Bundle的值
        sendOrderedBroadcast(intent,//意图动作,指定action动作
                null,//receiverPermission,接收这条广播具备什么权限
                new OrderFinalBroadcastReceiver(),//不管有没有停止发送广播，这个接受者一定会接收到
                null, //handler对象处理广播的分发
                0,//initialCode,初始代码
                null,//initialData,初始String数据
                data//Bundle对象
        );
    }

    private void normalBroadcast() {
        Intent intent = new Intent();
        intent.setAction(NormalBroadcastReceiver.MY_ACTION);
        Bundle data = new Bundle();
        data.putString(NormalBroadcastReceiver.MY_STRING_DATA, "发送广播了");
        intent.putExtra(NormalBroadcastReceiver.MY_DATA, data);
        sendBroadcast(intent);
    }

    private void service() {
        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        findViewById(R.id.stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        findViewById(R.id.bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService();
            }
        });

        findViewById(R.id.unbind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService();
            }
        });
    }

    private void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void bindService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        unbindService(serviceConnection);
    }
}
