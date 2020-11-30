package com.example.lawson.androidsummery.fourcomponent.activityabout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lawson.androidsummery.R;

public class AboutActivity extends AppCompatActivity {

    public static String RECOVER = "recover";
    public static String NEW_INTENT = "new_intent";

    private EditText editText;
    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Ian", "AboutActivity - onCreate");
        setContentView(R.layout.activity_about);
        editText = findViewById(R.id.text);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
                intent.putExtra(NEW_INTENT, "启动自己");
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, AboutSecondActivity.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, TaskAffinityActivity.class));
            }
        });
        if (savedInstanceState != null) {
            //onCreate正常启动时，savedInstanceState为null
            Log.i("Ian", savedInstanceState.getString(RECOVER));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String str = intent.getStringExtra(NEW_INTENT);
        Log.i("Ian", "onNewIntent : " + str);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Ian", "onSaveInstanceState");
        outState.putString(RECOVER, "保存的数据");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("Ian", "onRestoreInstanceState");
        String str = savedInstanceState.getString(RECOVER);
        editText.setText(str);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("Ian", "onConfigurationChanged : orientation-" + newConfig.orientation
                + " ; screenHeightDp-" + newConfig.screenHeightDp
                + " ; screenWidthDp-" + newConfig.screenWidthDp);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ian", "AboutActivity - onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        Log.i("Ian", "AboutActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Ian", "AboutActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Ian", "AboutActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ian", "AboutActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ian", "AboutActivity - onDestroy");
    }
}
