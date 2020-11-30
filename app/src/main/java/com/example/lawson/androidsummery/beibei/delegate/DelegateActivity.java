package com.example.lawson.androidsummery.beibei.delegate;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class DelegateActivity extends AppCompatActivity {


    private AppCompatDelegate mIanDelegate;

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        if (mIanDelegate == null) {
            //            mIanDelegate = IanDelegate.create(this, this);
            mIanDelegate = super.getDelegate();
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
