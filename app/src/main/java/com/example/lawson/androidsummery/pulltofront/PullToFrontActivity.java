package com.example.lawson.androidsummery.pulltofront;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.utils.ShellUtils;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * USER：lujianzhi
 * DATE：2024/8/7
 */
public class PullToFrontActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private Runnable getForegroundAppRunnable = new Runnable() {
        @Override
        public void run() {
            getForegroundApp(PullToFrontActivity.this);
            if (mHandler != null) {
                mHandler.postDelayed(getForegroundAppRunnable, 2000);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_front);
        findViewById(R.id.pull_up).setOnClickListener(v ->
                bringAppToFront(PullToFrontActivity.this, "com.guming.iot"));
        findViewById(R.id.front_app).setOnClickListener(v ->
                getForegroundApp(PullToFrontActivity.this));
        findViewById(R.id.loop_front_app).setOnClickListener(v ->
                mHandler.postDelayed(getForegroundAppRunnable, 2000));
        findViewById(R.id.running_app).setOnClickListener(v -> getRunningApps());
        findViewById(R.id.start_app).setOnClickListener(v -> startAPP());
        findViewById(R.id.kill_app).setOnClickListener(v -> killAPP());
    }

    private void killAPP() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShellUtils.execCommand("kill $(pidof com.ian.testapp com.example.lawson.androidsummery)", true, true);
            }
        }, 2000);
    }

    private void startAPP() {
        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.guming.iot");
        if (intent == null) {
            Toast.makeText(PullToFrontActivity.this, "未安装", Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(intent);
    }

    private void getRunningApps() {
        ShellUtils.execCommand("ps | grep com.", true, true);
    }

    private void bringAppToFront(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launchIntent);
        } else {
            // 处理没有找到应用的情况
            Log.i("ian", "App not found");
        }
    }

    private void getForegroundApp(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        String currentApp = null;
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        // 获取最近一段时间内的应用使用状态
        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, 0, time);
        if (appList != null && !appList.isEmpty()) {
            SortedMap<Long, UsageStats> sortedMap = new TreeMap<>();
            for (UsageStats usageStats : appList) {
                sortedMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (!sortedMap.isEmpty()) {
                currentApp = sortedMap.get(sortedMap.lastKey()).getPackageName();
            }
        }
        Log.i("ian", "currentApp - " + currentApp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(getForegroundAppRunnable);
        mHandler = null;
    }
}
