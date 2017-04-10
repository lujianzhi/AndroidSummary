package com.example.lawson.androidsummery.killprocess;

import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lawson.androidsummery.R;

public class KillProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_process);
        findViewById(R.id.kill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //只能自杀，不能他杀
                int pid = Process.myPid();
                Process.killProcess(pid);

                //只能他杀，不能自杀
//                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//                am.killBackgroundProcesses("com.example.lawson.androidsummery");
            }
        });
    }
}
