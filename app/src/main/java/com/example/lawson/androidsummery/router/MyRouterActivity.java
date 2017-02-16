package com.example.lawson.androidsummery.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;

@Router(value = {"ian2.com/ian_router/:name/:age/:sex", "root"}, intParams = "age", transfer = "name=>user")
public class MyRouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_router);

        String url = getIntent().getStringExtra(Routers.KEY_RAW_URL);

        String nameStr = "";
        nameStr = getIntent().getStringExtra("user");
        int ageStr = 0;
        ageStr = getIntent().getIntExtra("age", 0);
        String sexStr = "";
        sexStr = getIntent().getStringExtra("sex");
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(url + "\n" + nameStr + " " + ageStr + " " + sexStr);

    }
}
