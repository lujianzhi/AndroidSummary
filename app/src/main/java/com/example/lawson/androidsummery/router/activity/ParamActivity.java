package com.example.lawson.androidsummery.router.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;
import com.example.lawson.androidsummery.router.been.Person;
import com.google.gson.Gson;

@Route(path = Constant.PARAM_ACTIVITY)
public class ParamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);

        TextView valueTv = findViewById(R.id.value);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            valueTv.setText("无参");
            return;
        }
        String strValue = bundle.getString("strKey", "");
        int intValue = bundle.getInt("intKey", -1);
        Person person = new Gson().fromJson((String) bundle.get("objKey"), Person.class);

        String str = "String : " + strValue + " ; int : " + intValue + " ; person : " + person.toString();

        valueTv.setText(str);
    }
}
