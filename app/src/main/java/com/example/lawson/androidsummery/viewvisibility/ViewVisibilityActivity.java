package com.example.lawson.androidsummery.viewvisibility;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

@Route(path = Constant.VIEW_VISIBILITY_ACTIVITY)
public class ViewVisibilityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visibility);

        findViewById(R.id.view_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.VIEW_VISIBILITY_ACTIVITY_NORMAL_VIEW).navigation();
            }
        });

        findViewById(R.id.scroll_view_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.VIEW_VISIBILITY_ACTIVITY_SCROLL_VIEW).navigation();
            }
        });

        findViewById(R.id.list_view_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.VIEW_VISIBILITY_ACTIVITY_LIST_VIEW).navigation();
            }
        });
    }

}
