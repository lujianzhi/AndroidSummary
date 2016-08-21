package com.example.lawson.androidsummery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lawson.androidsummery.pointtopoint.DrawingView;

public class MainActivity extends AppCompatActivity {
    private DrawingView drawView;
    private float lineSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);
        lineSize = 10;
    }

}
