package com.example.lawson.androidsummery.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

import de.greenrobot.event.EventBus;

public class EventBusActivity extends AppCompatActivity {

    private TextView textView;
    private StickyObj obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        textView = (TextView) findViewById(R.id.text);

        textView.setText("lalala");
        EventBus.getDefault().registerSticky(EventBusActivity.this);

    }

    public void onEventMainThread(StickyObj obj){
        this.obj = obj;
        textView.setText("粘性事件");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(obj != null){
            EventBus.getDefault().removeStickyEvent(obj);
        }
    }
}
