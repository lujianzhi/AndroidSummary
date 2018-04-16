package com.example.lawson.androidsummery.viewvisibility;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

@Route(path = Constant.VIEW_VISIBILITY_ACTIVITY_SCROLL_VIEW)
public class ScrollViewVisibilityActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_visiblity);

        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        tv1 = (TextView) findViewById(R.id.tv_1);

        printViewVisibility(tv1);
    }

    private void printViewVisibility(final TextView textView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    Rect rect = new Rect();
                    scrollView.getHitRect(rect);
                    if (textView.getLocalVisibleRect(rect)) {
                        Log.i("Ian", textView.getText().toString() + " visible: " + true);
                        textView.setText("1 visible:" + true);
                    } else {
                        Log.i("Ian", "textView visible: " + false);
                        Toast.makeText(getBaseContext(), "textView visible: " + false, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
