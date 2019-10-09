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

    private static String TAG = "config1";
    private static String TAG2 = "config2";
    private static String TAG3 = "juhe";
    private static String TAG4 = "config4";
    private static String TAG5 = "config5";
    private static String TAG6 = "config6";
    private static String TAG7 = "config7";
    private static String TAG8 = "config8";
    private static String TAG9 = "config9";

    TextView text;
    TextView content1, content2, content3;
    TextView time;
    MMKV mmkv, mmkv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkv);
        String rootDir = MMKV.initialize(this);

        text = findViewById(R.id.text);
        text.setText("mmkv root: " + rootDir);

        mmkv = MMKV.defaultMMKV();
        mmkv2 = MMKV.mmkvWithID("second");
        content1 = findViewById(R.id.content1);
        content2 = findViewById(R.id.content2);
        content3 = findViewById(R.id.content3);

        time = findViewById(R.id.time);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okhttpAsynPost();
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
            mmkv.encode(TAG, configJson);
            mmkv2.encode(TAG2, configJson);
            mmkv.encode(TAG4, configJson);
            mmkv.encode(TAG5, configJson);
            mmkv.encode(TAG6, configJson);
            mmkv.encode(TAG7, configJson);
            mmkv2.encode(TAG8, configJson);
            mmkv2.encode(TAG9, configJson);
            content1.setText(mmkv.decodeString(TAG));
            content2.setText(mmkv2.decodeString(TAG2));
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
                        mmkv.encode(TAG3, json);
                        content3.setText(mmkv.decodeString(TAG3));
                    }
                });
            }
        });
    }
}
