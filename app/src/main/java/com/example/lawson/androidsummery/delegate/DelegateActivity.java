package com.example.lawson.androidsummery.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.IanDelegate;
import android.widget.TextView;
import com.example.lawson.androidsummery.R;

public class DelegateActivity extends AppCompatActivity {


    private AppCompatDelegate mIanDelegate;

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        if (mIanDelegate == null) {
            mIanDelegate = IanDelegate.create(this, this);
        }
        return mIanDelegate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate);

        TextView textView = findViewById(R.id.text_view);
        textView.setTag(ClickHelper.CLICK, "点击打点");
    }
}
