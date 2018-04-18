package com.example.lawson.androidsummery.androidtools.traceviewtool;

import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

@Route(path = Constant.ANDROID_TOOLS_ACTIVITY_TRACE_VIEW)
public class TraceViewToolActivity extends AppCompatActivity {

    private TextView one;
    private TextView two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_view_tool);
        Debug.startMethodTracing("ian_trace_view");

        one = (TextView) findViewById(R.id.state);
        two = (TextView) findViewById(R.id.state_two);

        sleep();

        download();
    }

    private void download() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
        final okhttp3.Request request = new okhttp3.Request.Builder().url("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=647672007,2716159314&fm=26&gp=0.jpg").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        two.setText(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final okhttp3.Response response) throws IOException {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                InputStream inputStream = response.body().byteStream();
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "b.jpg");
                OutputStream outputStream = new FileOutputStream(file);
                int temp;
                while ((temp = inputStream.read()) != -1) {
                    outputStream.write(temp);
                }
                outputStream.close();
                inputStream.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        two.setText(response.toString());
                    }
                });
            }
        });
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
            one.setText("睡眠一秒完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }
}
