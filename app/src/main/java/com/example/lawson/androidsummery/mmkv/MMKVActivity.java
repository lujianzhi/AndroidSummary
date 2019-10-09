package com.example.lawson.androidsummery.mmkv;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.tencent.mmkv.MMKV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MMKVActivity extends AppCompatActivity {

    private static String TAG = "config";

    TextView text;
    TextView content;
    TextView time;
    MMKV mmkv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkv);
        String rootDir = MMKV.initialize(this);

        text = findViewById(R.id.text);
        text.setText("mmkv root: " + rootDir);

        mmkv = MMKV.defaultMMKV();
        content = findViewById(R.id.content);

        time = findViewById(R.id.time);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                okhttpAsynPost();
                loadAssets();
            }
        });
    }

    private void loadAssets() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open("config.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            String configJson = stringBuilder.toString();
            long time1 = System.currentTimeMillis();
            mmkv.encode(TAG, configJson);
            long time2 = System.currentTimeMillis();
            long encodeTime = time2 - time1;

            long time3 = System.currentTimeMillis();
            content.setText(mmkv.decodeString(TAG));
            long time4 = System.currentTimeMillis();
            long decodeTime = time4 - time3;

            time.setText("encodeTime : " + encodeTime + " ; decodeTime : " + decodeTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void okhttpAsynPost() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10,
                TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
        RequestBody requestBody = new FormBody.Builder().add("type", "top").add("key",
                "a112f6137f862e6dadace2ff3489d093").build();
        okhttp3.Request request =
                new okhttp3.Request.Builder().url("http://v.juhe.cn/toutiao/index").post(requestBody).build();
        Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                //不在主线程
                Log.i("Ian", "onResponse - Thread : " + Thread.currentThread().getName());
                final String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        long time1 = System.currentTimeMillis();
                        mmkv.encode(TAG, json);
                        long time2 = System.currentTimeMillis();
                        long encodeTime = time2 - time1;

                        long time3 = System.currentTimeMillis();
                        content.setText(mmkv.decodeString(TAG));
                        long time4 = System.currentTimeMillis();
                        long decodeTime = time4 - time3;

                        time.setText("encodeTime : " + encodeTime + " ; decodeTime : " + decodeTime);
                    }
                });
            }
        });
    }
}
