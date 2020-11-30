package com.example.lawson.androidsummery.detectmemory;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.detectmemory.utils.Constant;

public class InnerClassActivity extends AppCompatActivity {

    //静态
    private static InnerClass innerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class);

        innerClass = new InnerClass();
        innerClass.setStr(Constant.STR);

    }

    class InnerClass{
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
