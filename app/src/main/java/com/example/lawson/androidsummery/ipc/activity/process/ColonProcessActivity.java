package com.example.lawson.androidsummery.ipc.activity.process;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.ipc.IPCActivity;
import com.example.lawson.androidsummery.ipc.binder.Book;

import java.util.List;

/**
 * android:process=":ian"
 * 使用":"开启新进程
 */
public class ColonProcessActivity extends AppCompatActivity {

    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colon_process);

        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        getProcessName();
        getBundleData();
    }

    /**
     * 获取当前进程名字
     */
    private void getProcessName() {
        int pid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfoList) {
            if (runningAppProcessInfo.pid == pid) {
                textView2.setText(runningAppProcessInfo.processName);
                break;
            }
        }
    }

    private void getBundleData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(IPCActivity.BUNDLE);
            if (bundle != null) {
                Book book = bundle.getParcelable(IPCActivity.BUNDLE_BOOK_KEY);
                if (book != null) {
                    textView3.setText(book.toString());
                } else {
                    textView3.setText("bundle.getParcelable(IPCActivity.BUNDLE_BOOK_KEY)");
                }
            } else {
                textView3.setText("intent.getBundleExtra(IPCActivity.BUNDLE)");
            }
        } else {
            textView3.setText("getIntent() == null");
        }
    }
}
