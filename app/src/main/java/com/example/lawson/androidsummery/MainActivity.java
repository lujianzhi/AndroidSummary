package com.example.lawson.androidsummery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import com.example.lawson.androidsummery.bitmap.BitmapActivity;
import com.example.lawson.androidsummery.collection.Collection_Activity;
import com.example.lawson.androidsummery.constraintlayout.ConstraintLayoutActivity;
import com.example.lawson.androidsummery.debug.DebugActivity;
import com.example.lawson.androidsummery.detectmemory.DetectMemoryActivity;
import com.example.lawson.androidsummery.diyview.DIYViewActivity;
import com.example.lawson.androidsummery.dragview.DragViewActivity;
import com.example.lawson.androidsummery.draw.DrawActivity;
import com.example.lawson.androidsummery.edittext.EditTextActivity;
import com.example.lawson.androidsummery.eventbus.EventBusActivity;
import com.example.lawson.androidsummery.eventbus.StickyObj;
import com.example.lawson.androidsummery.fragmentlazy.FragmentLazyLoadActivity;
import com.example.lawson.androidsummery.gesturedetector.GestureDetectorActivity;
import com.example.lawson.androidsummery.hidenavigationbar.HideNavigationBar;
import com.example.lawson.androidsummery.killprocess.KillProcessActivity;
import com.example.lawson.androidsummery.nohttp.NoHttpActivity;
import com.example.lawson.androidsummery.ongloballayoutlistener.OnGlobalLayoutListenerActivity;
import com.example.lawson.androidsummery.permission.PermissionActivity;
import com.example.lawson.androidsummery.pointtopoint.PointToPointActivity;
import com.example.lawson.androidsummery.popupwindow.PopupWindowActivity;
import com.example.lawson.androidsummery.pulltorefresh.PullToRefreshActivity;
import com.example.lawson.androidsummery.scroller.ScrollerActivity;
import com.example.lawson.androidsummery.spinner.SpinnerActivity;
import com.example.lawson.androidsummery.switchtheme.SwitchThemeActivity;
import com.example.lawson.androidsummery.thread.ThreadActivity;
import com.example.lawson.androidsummery.toast.ToastActivity;
import com.example.lawson.androidsummery.touchevent.ScrollEventActivity;
import com.example.lawson.androidsummery.touchevent.TouchEventActivity;
import com.example.lawson.androidsummery.velocitytracker.VelocityTrackerActivity;
import com.example.lawson.androidsummery.webview.WebViewActivity;
import com.github.mzule.activityrouter.router.Routers;

import java.util.regex.Pattern;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new StickyObj());
            }
        });

        findViewById(R.id.pointToPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PointToPointActivity.class));
            }
        });

        findViewById(R.id.bitmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BitmapActivity.class));
            }
        });

        findViewById(R.id.detect_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetectMemoryActivity.class));
            }
        });

        findViewById(R.id.collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Collection_Activity.class));
            }
        });

        findViewById(R.id.touch_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TouchEventActivity.class));
            }
        });

        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ToastActivity.class));
            }
        });

        findViewById(R.id.popup_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PopupWindowActivity.class));
            }
        });

        findViewById(R.id.event_bus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
            }
        });

        findViewById(R.id.hide_navigation_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HideNavigationBar.class));
            }
        });

        findViewById(R.id.pull_to_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PullToRefreshActivity.class));
            }
        });

        findViewById(R.id.nohttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoHttpActivity.class));
            }
        });

        findViewById(R.id.draw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DrawActivity.class));
            }
        });

        findViewById(R.id.ongloballayoutlistener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OnGlobalLayoutListenerActivity.class));
            }
        });

        findViewById(R.id.fragment_lazy_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FragmentLazyLoadActivity.class));
            }
        });

        findViewById(R.id.diy_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DIYViewActivity.class));
            }
        });

        findViewById(R.id.drag_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DragViewActivity.class));
            }
        });

        findViewById(R.id.edit_text_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
            }
        });

        findViewById(R.id.web_view_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });

        findViewById(R.id.debug_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DebugActivity.class));
            }
        });

        findViewById(R.id.scroll_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScrollEventActivity.class));
            }
        });

        findViewById(R.id.thread_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThreadActivity.class));
            }
        });

        findViewById(R.id.router_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Routers.open(MainActivity.this, "http://ian.com/ian_router/ian/23/male");
            }
        });

        TextView tv_customHyperLink = (TextView) findViewById(R.id.tv_customHyperLink);
        Pattern p = Pattern.compile("http://\\S*");
        Linkify.addLinks(tv_customHyperLink, p, "http");

        findViewById(R.id.permisson_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
            }
        });

        findViewById(R.id.spinner_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SpinnerActivity.class));
            }
        });

        findViewById(R.id.theme_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SwitchThemeActivity.class));
            }
        });

        findViewById(R.id.kill_process_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KillProcessActivity.class));
            }
        });

        findViewById(R.id.constraint_layout_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ConstraintLayoutActivity.class));
            }
        });

        findViewById(R.id.velocity_tracker_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VelocityTrackerActivity.class));
            }
        });

        findViewById(R.id.gesture_detector_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GestureDetectorActivity.class));
            }
        });

        findViewById(R.id.scroller_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScrollerActivity.class));
            }
        });

    }

}
