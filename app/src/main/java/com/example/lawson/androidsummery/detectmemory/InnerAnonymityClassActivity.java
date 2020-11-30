package com.example.lawson.androidsummery.detectmemory;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;

import java.util.Timer;
import java.util.TimerTask;

public class InnerAnonymityClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_anonymity_class);

        new Thread(){
            @Override
            public void run() {
                super.run();
                //线程中持有Activity的引用，且不释放
                while(true);
            }
        }.start();

    }

}
