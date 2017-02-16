package com.example.lawson.androidsummery.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.github.mzule.activityrouter.annotation.Router;

@Router(value = {"ian.com/ian/:name/:age/:sex", "root"}, intParams = "age")
public class MyRouter2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_router2);

        TextView textView = (TextView) findViewById(R.id.textView);
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);
        String sex = getIntent().getStringExtra("sex");
        textView.setText("name: " + name + " age: " + age + " sex:" + sex);
    }
}
