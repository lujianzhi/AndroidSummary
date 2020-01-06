package com.example.lawson.androidsummery.fastjson;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.lawson.androidsummery.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FastJsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json);

        TextView textView = findViewById(R.id.content);

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open("config.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            String configJson = stringBuilder.toString();
            JSONObject js = JSON.parseObject(configJson);
            String config = js.getString("app_system_config");
            textView.setText(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
