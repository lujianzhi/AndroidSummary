package com.example.lawson.androidsummery.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.lawson.androidsummery.R;

public class TouchEventActivity extends AppCompatActivity {

    private final String TAG = "lawson";
    private final String CURRENT = "TouchEventActivity : ";

    private MyLinearLayout linearLayout;
    private MyImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        linearLayout = (MyLinearLayout) findViewById(R.id.layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView = (MyImageView) findViewById(R.id.image_view);
//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(TouchEventActivity.this, "长按", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT + "dispatchTouchEvent : ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, CURRENT + "onTouchEvent : ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, CURRENT + "onTouchEvent : ACTION_UP");
                break;
        }

        return super.onTouchEvent(event);
    }
}
