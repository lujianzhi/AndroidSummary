package com.example.lawson.androidsummery.diyview;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.hideview.HideViewLVActivity;
import com.example.lawson.androidsummery.diyview.hideview.HideViewRVActivity;
import com.example.lawson.androidsummery.diyview.justifyTextView.JustifyTextViewActivity;
import com.example.lawson.androidsummery.diyview.learn2draw.AddableHorizontalTextViewActivity;
import com.example.lawson.androidsummery.diyview.learn2draw.ChangeLineViewGroupActivity;
import com.example.lawson.androidsummery.diyview.learn2draw.FadeInTextViewActivity;
import com.example.lawson.androidsummery.diyview.learn2draw.LoadingViewActivity;
import com.example.lawson.androidsummery.diyview.learn2draw.SubmitButtonActivity;

public class ViewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SubmitButtonActivity.class));
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoadingViewActivity.class));
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), FadeInTextViewActivity.class));
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ChangeLineViewGroupActivity.class));
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HideViewRVActivity.class));
            }
        });

        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HideViewLVActivity.class));
            }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AddableHorizontalTextViewActivity.class));
            }
        });

        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), JustifyTextViewActivity.class));
            }
        });
    }
}
