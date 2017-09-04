package com.example.lawson.androidsummery.remoteviews;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.example.lawson.androidsummery.MainActivity;
import com.example.lawson.androidsummery.R;

public class RemoteViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemoteViewsNotification();
            }
        });
    }

    /**
     * RemoteViews在Notification上的应用
     */
    private void showRemoteViewsNotification() {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_ONE_SHOT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_remote_views);
        RemoteViews bigRemoteViews = new RemoteViews(getPackageName(), R.layout.layout_big_remote_views);
        remoteViews.setOnClickPendingIntent(R.id.img, pendingIntent);
        remoteViews.setTextColor(R.id.read, getResources().getColor(R.color.colorPrimary));
        remoteViews.setTextViewText(R.id.write, "拉我，我可以变长");
        Notification notification = new Notification.Builder(this)
                //有了RemoteViews，就不显示内容和标题
                .setContentTitle("我是标题")
                //有了RemoteViews，就不显示内容和标题
                .setContentText("我是内容")
                .setSmallIcon(R.drawable.four_color)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        if (Build.VERSION.SDK_INT >= 16) {
            //最大高度为256dp
            notification.bigContentView = bigRemoteViews;
        }
        //最大高度为64dp
        notification.contentView = remoteViews;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    /**
     * 普通Notification
     */
    private void showNotification() {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_ONE_SHOT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.four_color)
                .setContentText("我是内容")
                .setContentTitle("我是标题")
                //设置PendingIntent
                .setContentIntent(pendingIntent)
                //设置被点击后自动清除
                .setAutoCancel(true)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
