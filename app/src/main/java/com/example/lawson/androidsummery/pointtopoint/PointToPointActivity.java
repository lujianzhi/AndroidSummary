package com.example.lawson.androidsummery.pointtopoint;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lawson.androidsummery.R;

public class PointToPointActivity extends AppCompatActivity {
    private DrawingView drawView;
    private float lineSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_to_point);
        drawView = findViewById(R.id.drawing);
        lineSize = 10;
    }
}
