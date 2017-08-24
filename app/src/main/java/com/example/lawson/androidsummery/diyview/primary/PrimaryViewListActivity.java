package com.example.lawson.androidsummery.diyview.primary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.primary.view.PrimaryViewActivity;
import com.example.lawson.androidsummery.diyview.primary.viewgroup.PrimaryViewGroupActivity;

public class PrimaryViewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_view_list);

        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrimaryViewListActivity.this, PrimaryViewActivity.class));
            }
        });

        findViewById(R.id.view_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrimaryViewListActivity.this, PrimaryViewGroupActivity.class));
            }
        });
    }
}
