package com.example.lawson.androidsummery.androidtools;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

@Route(path = Constant.ANDROID_TOOLS_ACTIVITY)
public class AndroidToolsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_tools);

        findViewById(R.id.trace_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ANDROID_TOOLS_ACTIVITY_TRACE_VIEW).navigation();
            }
        });

        findViewById(R.id.ui_auto_mator_viewer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ANDROID_TOOLS_ACTIVITY_UI_AUTO_MATOR_VIEWER).navigation();
            }
        });
    }
}
