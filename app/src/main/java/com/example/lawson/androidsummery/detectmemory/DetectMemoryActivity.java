package com.example.lawson.androidsummery.detectmemory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lawson.androidsummery.R;

import androidx.appcompat.app.AppCompatActivity;

public class DetectMemoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_memory);

        findViewById(R.id.single_oom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetectMemoryActivity.this, SingleOOMActivity.class));
            }
        });

        findViewById(R.id.inner_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetectMemoryActivity.this, InnerClassActivity.class));
            }
        });

        findViewById(R.id.inner_anonymity_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetectMemoryActivity.this, InnerAnonymityClassActivity.class));
            }
        });

        findViewById(R.id.handler_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetectMemoryActivity.this, HandlerLeakActivity.class));
            }
        });

        findViewById(R.id.thread_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetectMemoryActivity.this, ThreadTimerActivity.class));
            }
        });

        findViewById(R.id.listener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetectMemoryActivity.this, ListenerActivity.class));
            }
        });

        findViewById(R.id.collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetectMemoryActivity.this, MemoryCollectionActivity.class));
            }
        });

    }
}
