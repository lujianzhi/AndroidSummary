package com.example.lawson.androidsummery.timeline;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.lawson.androidsummery.R;
import java.io.File;
import java.util.ArrayList;

public class WXTimelineShareActivity extends AppCompatActivity {

    ArrayList<String> paths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxtimeline_share);

        File myFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "DCIM" + File.separator + "Camera");

        File[] files = myFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith("jpg")) {
                    if (paths.size() < 3) {
                        paths.add(file.getAbsolutePath());
                    } else {
                        break;
                    }
                }

            }
        }

        if (!isApkInstalled(this, "com.tencent.mm")) {
            Toast.makeText(this, "没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }

        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    private void share() {
        Log.i("Ian", paths.toString());
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(componentName);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        ArrayList<Uri> uris = new ArrayList<>();
        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                uris.add(Uri.fromFile(file));
            }
        }

        if (uris.size() == 0) {
            Toast.makeText(this, "没有图片", Toast.LENGTH_SHORT).show();
            return;
        }

        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uris);
        intent.putExtra("Kdescription", "Ian");
        startActivity(intent);
    }

    /**
     * 判断一个apk是否安装
     */
    public boolean isApkInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

}
