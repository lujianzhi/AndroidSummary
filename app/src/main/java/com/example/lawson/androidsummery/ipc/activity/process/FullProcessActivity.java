package com.example.lawson.androidsummery.ipc.activity.process;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.ipc.IPCActivity;
import com.example.lawson.androidsummery.ipc.been.MySerializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import static com.example.lawson.androidsummery.R.id.textView2;

/**
 * android:process="com.example.lawson.androidsummery.ian"
 */
public class FullProcessActivity extends AppCompatActivity {

    private TextView processNameTv;
    private TextView fileDataTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_process);

        processNameTv = findViewById(R.id.textView);
        fileDataTv = findViewById(textView2);

        getProcessName();
        loadObjFromSDCard();
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
                processNameTv.setText(runningAppProcessInfo.processName);
                break;
            }
        }
    }

    /**
     * 反序列化
     */
    private void loadObjFromSDCard() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                File file = new File(IPCActivity.FILE_PATH);
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    final MySerializable mySerializable = (MySerializable) objectInputStream.readObject();
                    FullProcessActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fileDataTv.setText("读取 : \n" + mySerializable.toString());
                        }
                    });
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
