package com.example.lawson.androidsummery.viewvisibility;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

@Route(path = Constant.VIEW_VISIBILITY_ACTIVITY_NORMAL_VIEW)
public class NormalViewVisibilityActivity extends AppCompatActivity {

    private ImageView canSee;
    private ImageView canNotSee;
    private Button calBtn;
    private TextView logTv;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_view_visibility);

        canSee = findViewById(R.id.can_see);
        canNotSee = findViewById(R.id.can_not_see);
        logTv = findViewById(R.id.log);
        calBtn = findViewById(R.id.cal);

        stringBuilder = new StringBuilder();

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printVisible("canNotSee_ImageView", canNotSee);
                printVisible("canSee_ImageView", canSee);
                logTv.setText(stringBuilder.toString());
            }
        });

    }

    private void printVisible(String name, View view) {
        Rect rect = new Rect();
        //visible就是可见性
        //getGlobalVisibleRect(rect)只能检查出这个View在手机屏幕（或者说是相对它的父View）的位置，而不能检查出与其他兄弟View的相对位置。
        boolean visible = view.getGlobalVisibleRect(rect);
        //getLocalVisibleRect(rect)获得的rect坐标系的原点是View自己的左上角
        boolean visible2 = view.getLocalVisibleRect(rect);
        stringBuilder.append(name + " visible : " + visible + " rect : " + rect + "\n");
    }
}
