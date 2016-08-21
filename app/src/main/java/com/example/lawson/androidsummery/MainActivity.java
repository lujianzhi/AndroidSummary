package com.example.lawson.androidsummery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lawson.androidsummery.bitmap.BitmapActivity;
import com.example.lawson.androidsummery.pointtopoint.DrawingView;
import com.example.lawson.androidsummery.pointtopoint.PointToPointActivity;

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

    }

}
