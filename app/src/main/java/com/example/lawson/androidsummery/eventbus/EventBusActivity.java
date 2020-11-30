package com.example.lawson.androidsummery.eventbus;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

    private TextView textView;
    private StickyObj obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        textView = findViewById(R.id.text);

        textView.setText("lalala");
        EventBus.getDefault().register(EventBusActivity.this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void thisIsStickyEvent(StickyObj obj) {
        this.obj = obj;
        textView.setText("粘性事件");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (obj != null) {
            EventBus.getDefault().removeStickyEvent(obj);
        }
    }
}
