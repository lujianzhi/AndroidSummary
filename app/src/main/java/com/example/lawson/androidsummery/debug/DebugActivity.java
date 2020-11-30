package com.example.lawson.androidsummery.debug;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

public class DebugActivity extends AppCompatActivity {

    private TextView number;

    private Long num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        number = findViewById(R.id.number);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            sb.append("第" + i + "个");
        }

        number.setText(sb.toString());
    }
}
