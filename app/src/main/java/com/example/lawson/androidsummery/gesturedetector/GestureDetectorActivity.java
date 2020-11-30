package com.example.lawson.androidsummery.gesturedetector;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;

public class GestureDetectorActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);

        iv = findViewById(R.id.iv);

    }
}
