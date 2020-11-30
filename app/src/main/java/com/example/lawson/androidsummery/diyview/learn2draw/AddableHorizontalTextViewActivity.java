package com.example.lawson.androidsummery.diyview.learn2draw;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.lawson.androidsummery.R;

public class AddableHorizontalTextViewActivity extends AppCompatActivity {

    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addable_horizontal_text_view);

        mContainer = findViewById(R.id.container);

        for (int i = 0; i < 3; i++) {
            View textViewContainer = LayoutInflater.from(this).inflate(R.layout.frame_text_view, null);
            TextView textView = textViewContainer.findViewById(R.id.text_view);
            textView.setText(String.valueOf(i));
            if (i == 1) {
//                textView.setBackground(null);
            }
            mContainer.addView(textViewContainer);
        }
    }
}
