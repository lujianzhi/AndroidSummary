package com.example.lawson.androidsummery.diyview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.bord.BordActivity;
import com.example.lawson.androidsummery.diyview.primary.PrimaryViewListActivity;
import com.example.lawson.androidsummery.diyview.view.FirstView;

public class DIYViewActivity extends Activity {

    private FirstView firstView;
    private Button enter;
    private Button primary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyview);

        enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ViewListActivity.class));
            }
        });

        findViewById(R.id.primary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), PrimaryViewListActivity.class));
            }
        });

        findViewById(R.id.bord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), BordActivity.class));
            }
        });

        findViewById(R.id.color_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), BordActivity.class));
            }
        });

        //        firstView = (FirstView) findViewById(R.id.first_view);
        //
        //        new Thread(firstView).start();

    }
}
