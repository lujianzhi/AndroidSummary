package com.example.lawson.androidsummery.router.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;
import com.example.lawson.androidsummery.router.been.Person;

@Route(path = Constant.AROUTER_ACTIVITY)
public class ARouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);

        findViewById(R.id.param).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PARAM_ACTIVITY)
                        .withString("strKey", "Ian")
                        .withInt("intKey", 24)
                        .withObject("objKey", new Person("Ian", 24))
                        .navigation();
            }
        });

        findViewById(R.id.for_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.FOR_RESULT_ACTIVITY)
                        .navigation(ARouterActivity.this, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 200:
                Toast.makeText(getBaseContext(), "resultCode : " + resultCode, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
