package com.example.lawson.androidsummery.diyview.learn2draw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.diyview.learn2draw.view.ChangeLineViewGroup;

public class ChangeLineViewGroupActivity extends AppCompatActivity {

    private ChangeLineViewGroup change_line;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_line_view_group);
        change_line = (ChangeLineViewGroup) findViewById(R.id.change_line);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button1.setLayoutParams(params);
        button2.setLayoutParams(params);
        button3.setLayoutParams(params);
        button4.setLayoutParams(params);
    }
}
