package com.example.lawson.androidsummery.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.thread.asynctask.MyAsyncTask;
import com.example.lawson.androidsummery.thread.intentservice.MyIntentService;

public class AndroidThreadActivity extends AppCompatActivity {

    private TextView progressTv;
    private TextView contentTv;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_thread);

        progressTv = findViewById(R.id.progress);
        contentTv = findViewById(R.id.content);

        findViewById(R.id.async_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask();
            }
        });

        findViewById(R.id.cancel_async_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myAsyncTask != null) {
                    myAsyncTask.cancel(true);
                }
            }
        });

        findViewById(R.id.intent_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentService();
            }
        });
    }

    private void intentService() {
        Intent service = new Intent(this, MyIntentService.class);
        service.putExtra(MyIntentService.TASK_ACTION, MyIntentService.TASK_ONE);
        startService(service);
        service.putExtra(MyIntentService.TASK_ACTION, MyIntentService.TASK_TWO);
        startService(service);
        service.putExtra(MyIntentService.TASK_ACTION, MyIntentService.TASK_THREE);
        startService(service);
    }

    private void asyncTask() {
        myAsyncTask = new MyAsyncTask(new MyAsyncTask.IUpdateUI() {
            @Override
            public void onProgressUpdate(int progress) {
                progressTv.setText("当前进度 : " + progress + " %");
            }

            @Override
            public void onPostExecute() {
                contentTv.setText("任务完成");
            }

            @Override
            public void onCancelled() {
                contentTv.setText("任务onCancelled");
            }

            @Override
            public void onCancelled(Void aVoid) {
                contentTv.setText("任务onCancelled(Void aVoid)");
            }
        });
        myAsyncTask.execute();
    }
}
