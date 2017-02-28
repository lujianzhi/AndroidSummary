package com.example.lawson.androidsummery.nohttp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.nohttp.yanzhenjie.OriginActivity;

public class NoHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_http);

        findViewById(R.id.nohttp_origin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoHttpActivity.this, OriginActivity.class);
                NoHttpActivity.this.startActivity(intent);
            }
        });

    }
}
