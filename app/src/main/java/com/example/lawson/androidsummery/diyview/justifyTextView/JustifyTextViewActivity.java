package com.example.lawson.androidsummery.diyview.justifyTextView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

public class JustifyTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justify_text_view);

        final TextView textView = findViewById(R.id.text_view);
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("你好qwertyuiopasdfghjkl");
            }
        });
    }
}
