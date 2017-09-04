package com.example.lawson.androidsummery.remoteviews;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.lawson.androidsummery.MainActivity;
import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2017/9/4.
 * Project : AndroidSummary
 */

public class MyAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i("Ian", "MyAppWidgetProvider -> onReceive");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i("Ian", "MyAppWidgetProvider -> onUpdate");

        final int counter = appWidgetIds.length;
        for (int i = 0; i < counter; i++) {
            int appWidgetId = appWidgetIds[i];
            Log.i("Ian", "appWidgetId : " + appWidgetId);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_ONE_SHOT);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            remoteViews.setTextViewText(R.id.name, "哟哟哟，wasup");
            remoteViews.setOnClickPendingIntent(R.id.image_view, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i("Ian", "MyAppWidgetProvider -> onEnabled");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i("Ian", "MyAppWidgetProvider -> onDisabled");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i("Ian", "MyAppWidgetProvider -> onDeleted");
    }
}
