package com.example.lawson.androidsummery.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

import java.io.File;
import java.io.IOException;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button button;
    private Button button2;
    private final int ASK_WRITE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        textView = (TextView) findViewById(R.id.text_view);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    private void write() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/a.text");
        if (file.exists()) {
            file.delete();
        }
        try {
            if (file.createNewFile()) {
                textView.setText("保存成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestPermisson() {
        int permissionState = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionState != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showRequestPermissionDialog("你需要去重新申请权限", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, ASK_WRITE_PERMISSION);
                    }
                });
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, ASK_WRITE_PERMISSION);
            }
        } else {
            Toast.makeText(PermissionActivity.this, "已经拥有权限", Toast.LENGTH_SHORT).show();
        }
    }

    private void showRequestPermissionDialog(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(PermissionActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ASK_WRITE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PermissionActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PermissionActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                write();
                break;
            case R.id.button2:
                requestPermisson();
                break;
            default:
                break;
        }
    }

}
