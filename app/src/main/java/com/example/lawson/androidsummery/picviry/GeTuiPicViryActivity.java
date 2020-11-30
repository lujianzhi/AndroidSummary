package com.example.lawson.androidsummery.picviry;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lawson.androidsummery.MainActivity;
import com.example.lawson.androidsummery.R;
import com.getui.gysdk.GYManager;
import com.getui.gysdk.GYResponse;
import com.getui.gysdk.PicCallBack;

public class GeTuiPicViryActivity extends AppCompatActivity {

    private String TAG = "Ian";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_tui_pic_viry);


        findViewById(R.id.getui_pic_vir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GYManager.getInstance().picVerify(GeTuiPicViryActivity.this, "20180620a", true, new PicCallBack() {
                    //动画验证码准备完成，即将展示.
                    @Override
                    public void onPicReady(GYResponse response) {
                        //Loading框在该方法调用之前就已经出现,执行该方法前会有一个耗时操作
                        //生成验证码弹窗在这里执行后会弹出
                        Log.d(TAG, "pic is ready:" + response);
                        Toast.makeText(GeTuiPicViryActivity.this, "准备工作", Toast.LENGTH_SHORT).show();
                    }

                    //验证通过
                    @Override
                    public void onSuccess(final GYResponse response) {
                        //验证通过时调用
                        Log.d(TAG, "verify success:" + response);
                        Toast.makeText(GeTuiPicViryActivity.this, "验证通过", Toast.LENGTH_SHORT).show();
                    }

                    //验证失败
                    @Override
                    public void onFailed(final GYResponse response) {
                        //测试了几次,在点击取消的时候会执行,验证码输入错误时不调用
                        Log.d(TAG, "verify failed:" + response);
                        Toast.makeText(GeTuiPicViryActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
