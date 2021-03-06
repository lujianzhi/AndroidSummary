package com.example.lawson.androidsummery.diyview.dragview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.dragview.view.DemoView;

public class DragViewActivity extends AppCompatActivity {
    private DemoView demoView;
    private ScrollView top;
    private LinearLayout content_area;
    private RelativeLayout activity_drag_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
        top = findViewById(R.id.top);
        demoView = findViewById(R.id.demo_view);
        content_area = findViewById(R.id.content_area);
        activity_drag_view = findViewById(R.id.activity_drag_view);
        if (activity_drag_view != null) {
            activity_drag_view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    demoView.setScreenHeight(activity_drag_view.getHeight());
                    activity_drag_view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
        demoView.setiActionUp(new DemoView.IActionUp() {
            @Override
            public void actionUp(int offY) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) top.getLayoutParams();
                params.height = top.getHeight() + offY;
//                Log.i("Ian", "增量offY : " + offY);
//                Log.i("Ian", "TextView高params.height : " + params.height);
                top.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) content_area.getLayoutParams();
                params1.height = content_area.getHeight() - offY;
//                Log.i("Ian", "LinearLayout高params.height : " + params1.height);
                content_area.setLayoutParams(params1);
                Log.i("Ian", "----------------------------------------------");
            }
        });
    }
}
