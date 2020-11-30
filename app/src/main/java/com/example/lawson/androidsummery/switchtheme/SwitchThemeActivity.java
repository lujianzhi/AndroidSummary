package com.example.lawson.androidsummery.switchtheme;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class SwitchThemeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout bg;
    private TextView text;
    private Button day;
    private Button night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.IanTheme_Day);
        setContentView(R.layout.activity_switch_theme);

        bg = findViewById(R.id.bg);
        text = findViewById(R.id.text);
        day = findViewById(R.id.day);
        night = findViewById(R.id.night);

        day.setOnClickListener(this);
        night.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day:
                switchTheme(R.style.IanTheme_Day);
                break;

            case R.id.night:
                switchTheme(R.style.IanTheme_Night);
                break;
        }
    }

    private void switchTheme(int resId) {
        setTheme(resId);
        setBackground(bg, R.attr.app_bg_color);
        setBackground(text, R.attr.app_text_bg_color);
        setTextColor(text, R.attr.app_text_color);
    }

    private void setTextColor(TextView view, int attrId) {
        Resources.Theme theme = getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrId, typedValue, true);
        view.setTextColor(ResourcesCompat.getColorStateList(getResources(), typedValue.resourceId, getTheme()));

    }

    private void setBackground(View view, int attrId) {
        Resources.Theme theme = getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(attrId, typedValue, true);
        view.setBackgroundResource(typedValue.resourceId);
    }
}
