package com.example.lawson.androidsummery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lawson.androidsummery.bitmap.BitmapActivity;
import com.example.lawson.androidsummery.collection.Collection_Activity;
import com.example.lawson.androidsummery.detectmemory.MemoryCollectionActivity;
import com.example.lawson.androidsummery.detectmemory.DetectMemoryActivity;
import com.example.lawson.androidsummery.pointtopoint.PointToPointActivity;
import com.example.lawson.androidsummery.popupwindow.PopupWindowActivity;
import com.example.lawson.androidsummery.toast.ToastActivity;
import com.example.lawson.androidsummery.touchevent.TouchEventActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.pointToPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PointToPointActivity.class));
            }
        });

        findViewById(R.id.bitmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BitmapActivity.class));
            }
        });

        findViewById(R.id.detect_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetectMemoryActivity.class));
            }
        });

        findViewById(R.id.collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Collection_Activity.class));
            }
        });

        findViewById(R.id.touch_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TouchEventActivity.class));
            }
        });

        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ToastActivity.class));
            }
        });

        findViewById(R.id.popup_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PopupWindowActivity.class));
            }
        });

    }

}
