package com.example.lawson.androidsummery.detectmemory;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.detectmemory.utils.SingleOOM;

import androidx.appcompat.app.AppCompatActivity;

public class SingleOOMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_oom);

        String reasonText = "退回上一个界面，等待几秒就会出现内存泄露的报告。";

        TextView reason = findViewById(R.id.reason);
        reason.setText(reasonText);

        //TextView，在xml解析的时候用到了context对象，与当前的Activity关联，故会内存泄露
        SingleOOM.getInstance().setTextView(reason);

        //字符串，与当前的Activity没有关联，故不会内存泄露
//        SingleOOM.getInstance().setFromActivity(reasonText);

        //与当前Activity没关联，不会内存泄露
//        ObjWithoutContext objWithoutContext = new ObjWithoutContext();
//        SingleOOM.getInstance().setObjWithoutContext(objWithoutContext);

        //与当前Activity关联，会出现内存泄露
//        ObjWithContext objWithContext = new ObjWithContext(this);
//        SingleOOM.getInstance().setObjWithContext(objWithContext);


    }
}
