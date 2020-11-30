package com.example.lawson.androidsummery.beibei;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.beibei.delegate.DelegateActivity;
import com.example.lawson.androidsummery.beibei.hbaction.ActionActivity;

public class BeiBeiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_bei);

        findViewById(R.id.delegate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BeiBeiActivity.this, DelegateActivity.class));
            }
        });

        findViewById(R.id.action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BeiBeiActivity.this, ActionActivity.class));
            }
        });

    }
}
