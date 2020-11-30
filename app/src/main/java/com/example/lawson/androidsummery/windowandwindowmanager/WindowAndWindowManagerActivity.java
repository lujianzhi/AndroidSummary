package com.example.lawson.androidsummery.windowandwindowmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lawson.androidsummery.R;

public class WindowAndWindowManagerActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button floatingButton;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;
    private final int ASK_WRITE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_and_window_manager);

        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(getBaseContext())) {
                showFloatingButton();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        } else {
            showFloatingButton();
        }


    }

    private void showFloatingButton() {
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        floatingButton = new Button(getBaseContext());
        floatingButton.setText("我是WindowManager创造出来的");
        floatingButton.setOnTouchListener(this);
        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
        layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        layoutParams.gravity = Gravity.START | Gravity.TOP;
        layoutParams.x = 100;
        layoutParams.y = 300;
        windowManager.addView(floatingButton, layoutParams);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                layoutParams.x = rawX;
                layoutParams.y = rawY;
                Log.i("Ian", "x : " + rawX);
                Log.i("Ian", "y : " + rawY);
                windowManager.updateViewLayout(floatingButton, layoutParams);
                break;
        }
        return false;
    }

    private void showRequestPermissionDialog(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(WindowAndWindowManagerActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
