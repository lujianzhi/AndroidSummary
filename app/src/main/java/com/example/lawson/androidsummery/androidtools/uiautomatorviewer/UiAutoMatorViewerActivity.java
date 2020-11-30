package com.example.lawson.androidsummery.androidtools.uiautomatorviewer;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

@Route(path = Constant.ANDROID_TOOLS_ACTIVITY_UI_AUTO_MATOR_VIEWER)
public class UiAutoMatorViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_auto_mator_viewer);

        final ViewStub viewStub = findViewById(R.id.view_stub);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //inflate一次后viewStub会置空，所以要用View来代替
                final View view = viewStub.inflate();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        }, 2000);
    }
}
