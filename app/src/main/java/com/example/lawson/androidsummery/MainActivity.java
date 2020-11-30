package com.example.lawson.androidsummery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lawson.androidsummery.animation.AnimationActivity;
import com.example.lawson.androidsummery.beibei.BeiBeiActivity;
import com.example.lawson.androidsummery.bitmap.BitmapActivity;
import com.example.lawson.androidsummery.bitmap.BitmapAndCacheActivity;
import com.example.lawson.androidsummery.collection.Collection_Activity;
import com.example.lawson.androidsummery.constraintlayout.ConstraintLayoutActivity;
import com.example.lawson.androidsummery.databinding.DataBindingActivity;
import com.example.lawson.androidsummery.debug.DebugActivity;
import com.example.lawson.androidsummery.detectmemory.DetectMemoryActivity;
import com.example.lawson.androidsummery.diyview.DIYViewActivity;
import com.example.lawson.androidsummery.diyview.dragview.DragViewActivity;
import com.example.lawson.androidsummery.diyview.timepicker.DatePickerActivity;
import com.example.lawson.androidsummery.diyview.timer.TimerManager;
import com.example.lawson.androidsummery.drawerlayout.DrawerLayoutActivity;
import com.example.lawson.androidsummery.edittext.EditTextActivity;
import com.example.lawson.androidsummery.eventbus.EventBusActivity;
import com.example.lawson.androidsummery.eventbus.StickyObj;
import com.example.lawson.androidsummery.fastjson.FastJsonActivity;
import com.example.lawson.androidsummery.fourcomponent.FourComponentActivity;
import com.example.lawson.androidsummery.fourcomponent.activityabout.AboutActivity;
import com.example.lawson.androidsummery.fourcomponent.activityabout.MyFragmentActivity;
import com.example.lawson.androidsummery.fragment.normal.FragmentNormalActivity;
import com.example.lawson.androidsummery.gesturedetector.GestureDetectorActivity;
import com.example.lawson.androidsummery.handler.HandlerPolicyActivity;
import com.example.lawson.androidsummery.hencoder.HenCoderActivity;
import com.example.lawson.androidsummery.hidenavigationbar.HideNavigationBar;
import com.example.lawson.androidsummery.ipc.IPCActivity;
import com.example.lawson.androidsummery.junittest.UITestActivity;
import com.example.lawson.androidsummery.killprocess.KillProcessActivity;
import com.example.lawson.androidsummery.levelcount.LevelCountActivity;
import com.example.lawson.androidsummery.lottie.LottieActivity;
import com.example.lawson.androidsummery.mmkv.MMKVActivity;
import com.example.lawson.androidsummery.mvp.view.NewsActivity;
import com.example.lawson.androidsummery.net.ui.NetActivity;
import com.example.lawson.androidsummery.nohttp.NoHttpActivity;
import com.example.lawson.androidsummery.ongloballayoutlistener.OnGlobalLayoutListenerActivity;
import com.example.lawson.androidsummery.permission.PermissionActivity;
import com.example.lawson.androidsummery.picviry.GeTuiPicViryActivity;
import com.example.lawson.androidsummery.pointtopoint.PointToPointActivity;
import com.example.lawson.androidsummery.popupwindow.PopupWindowActivity;
import com.example.lawson.androidsummery.pulltorefresh.PullToRefreshActivity;
import com.example.lawson.androidsummery.record.RecordDialogManager;
import com.example.lawson.androidsummery.recyclerview.RecyclerViewsActivity;
import com.example.lawson.androidsummery.remoteviews.RemoteViewsActivity;
import com.example.lawson.androidsummery.router.Constant;
import com.example.lawson.androidsummery.rxjava.RxJava2Activity;
import com.example.lawson.androidsummery.scroller.ScrollerActivity;
import com.example.lawson.androidsummery.securitycheck.SecurityCheckActivity;
import com.example.lawson.androidsummery.spinner.SpinnerActivity;
import com.example.lawson.androidsummery.switchtheme.SwitchThemeActivity;
import com.example.lawson.androidsummery.takephoto.TakePhotoActivity;
import com.example.lawson.androidsummery.test.ModelOne;
import com.example.lawson.androidsummery.test.ModelThree;
import com.example.lawson.androidsummery.test.ModelTwo;
import com.example.lawson.androidsummery.test.TestActivity;
import com.example.lawson.androidsummery.thread.AndroidThreadActivity;
import com.example.lawson.androidsummery.thread.ThreadActivity;
import com.example.lawson.androidsummery.timeline.WXTimelineShareActivity;
import com.example.lawson.androidsummery.toast.ToastActivity;
import com.example.lawson.androidsummery.touchevent.ScrollEventActivity;
import com.example.lawson.androidsummery.touchevent.TouchEventActivity;
import com.example.lawson.androidsummery.velocitytracker.VelocityTrackerActivity;
import com.example.lawson.androidsummery.viewserver.ViewServer;
import com.example.lawson.androidsummery.webview.WebViewActivity;
import com.example.lawson.androidsummery.windowandwindowmanager.WindowAndWindowManagerActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import me.ele.uetool.UETool;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewServer.get(this).addWindow(this);

        timer();

        findViewById(R.id.beibei).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BeiBeiActivity.class));
            }
        });

        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelTwo modelTwo = new ModelTwo("2");
                ModelThree modelThree = new ModelThree();
                modelThree.name = "3";
                ModelOne modelOne = new ModelOne(modelTwo, modelThree);
                Bundle bundle = new Bundle();
                bundle.putParcelable("obj", modelOne);

                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

        findViewById(R.id.hen_coder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HenCoderActivity.class));
            }
        });

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

        findViewById(R.id.ongloballayoutlistener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OnGlobalLayoutListenerActivity.class));
            }
        });

        findViewById(R.id.fragment_lazy_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FragmentNormalActivity.class));
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

        findViewById(R.id.android_thread_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viw) {
                startActivity(new Intent(MainActivity.this, AndroidThreadActivity.class));
            }
        });

        findViewById(R.id.arouter_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(Constant.AROUTER_ACTIVITY).navigation();
            }
        });

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

        findViewById(R.id.about_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        findViewById(R.id.about_activity_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyFragmentActivity.class));
            }
        });

        findViewById(R.id.handler_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HandlerPolicyActivity.class));
            }
        });

        findViewById(R.id.rxjava2_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RxJava2Activity.class));
            }
        });

        findViewById(R.id.window_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WindowAndWindowManagerActivity.class));
            }
        });

        findViewById(R.id.animation_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
            }
        });

        findViewById(R.id.take_photo_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TakePhotoActivity.class));
            }
        });

        findViewById(R.id.recycler_view_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecyclerViewsActivity.class));
            }
        });

        findViewById(R.id.net_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NetActivity.class));
            }
        });

        findViewById(R.id.android_tool_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(Constant.ANDROID_TOOLS_ACTIVITY).navigation();
            }
        });

        findViewById(R.id.data_binding_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
            }
        });

        findViewById(R.id.mvp_rxjava_retrofit_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });

        findViewById(R.id.ipc_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, IPCActivity.class));
            }
        });

        findViewById(R.id.remoteviews_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RemoteViewsActivity.class));
            }
        });

        findViewById(R.id.bitmap_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BitmapAndCacheActivity.class));
            }
        });

        findViewById(R.id.component_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FourComponentActivity.class));
            }
        });

        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error();
            }
        });

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UITestActivity.class));
            }
        });

        createDir();

        findViewById(R.id.record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordDialogManager.getInstance().startRecording(getSupportFragmentManager(), getApplicationContext());
            }
        });

        findViewById(R.id.level_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LevelCountActivity.class));
            }
        });

        findViewById(R.id.view_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(Constant.VIEW_VISIBILITY_ACTIVITY).navigation();
            }
        });

        findViewById(R.id.text_view_support_html).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(Constant.TEXT_VIEW_SUPPORT_HTML).navigation();
            }
        });

        findViewById(R.id.share_multi_pics_to_time_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WXTimelineShareActivity.class));
            }
        });

        findViewById(R.id.lottie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LottieActivity.class));
            }
        });

        findViewById(R.id.date_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DatePickerActivity.class));
            }
        });

        findViewById(R.id.getui_pic_viry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GeTuiPicViryActivity.class));
            }
        });

        findViewById(R.id.eleme_uetool_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UETool.showUETMenu();
            }
        });
        findViewById(R.id.eleme_uetool_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UETool.dismissUETMenu();
            }
        });

        findViewById(R.id.drawer_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
            }
        });

        findViewById(R.id.security_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecurityCheckActivity.class));
            }
        });

        findViewById(R.id.mmkv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MMKVActivity.class));
            }
        });

        findViewById(R.id.fastjson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FastJsonActivity.class));
            }
        });

    }

    private void createDir() {
        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record_temp";
        String recordPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record";

        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        File recordFile = new File(recordPath);
        if (!recordFile.exists()) {
            recordFile.mkdir();
        }
    }

    private TimerManager timerManager;

    private void timer() {
        TextView textView = findViewById(R.id.time);
        timerManager = new TimerManager(textView, TimerManager.CLOCKWISE, 188000);
        timerManager.startTiming();
    }

    private void error() {
        try {
            File file = null;
            file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("德玛西亚出错了");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timerManager.releaseTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
}
