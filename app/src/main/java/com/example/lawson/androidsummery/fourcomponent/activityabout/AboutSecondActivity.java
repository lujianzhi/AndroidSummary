package com.example.lawson.androidsummery.fourcomponent.activityabout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

public class AboutSecondActivity extends AppCompatActivity {

    private Button button;
    private String ACTION_ONE = "action_one";
    private String ACTION_TWO = "action_two";
    private String ACTION_THREE = "action_three";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_second);

        Log.i("Ian", "AboutSecondActivity - onCreate");

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action测试
//                Intent intent = new Intent();
//                intent.setAction(ACTION_THREE);
//                intent.setAction(ACTION_ONE);
//                startActivity(intent);

                //category测试，代码中没有添加，但是会自动默认会添加android.intent.category.DEFAULT
                Intent intent = new Intent();
                intent.setAction(ACTION_ONE);
                intent.setAction(ACTION_THREE);
                if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                    //判断有没有匹配intent的activity
                    startActivity(intent);
                } else {
                    Toast.makeText(AboutSecondActivity.this, "没有找到匹配的Activity", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ian", "AboutSecondActivity - onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ian", "AboutSecondActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ian", "AboutSecondActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ian", "AboutSecondActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ian", "AboutSecondActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ian", "AboutSecondActivity - onDestroy");
    }
}
