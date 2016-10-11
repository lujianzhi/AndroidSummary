package com.example.lawson.androidsummery.hidenavigationbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;

public class HideNavigationBar extends AppCompatActivity implements View.OnClickListener {

    Button main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_navigation_bar);

        main = (Button) findViewById(R.id.hide);
        main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = main.getSystemUiVisibility();
                if (i == View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) {//2
                    main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } else if (i == View.SYSTEM_UI_FLAG_VISIBLE) {//0
                    main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                } else if (i == View.SYSTEM_UI_FLAG_LOW_PROFILE) {//1
                    main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
