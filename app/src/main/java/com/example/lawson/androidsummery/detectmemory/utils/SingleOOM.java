package com.example.lawson.androidsummery.detectmemory.utils;

import android.content.Context;
import android.widget.TextView;

import com.example.lawson.androidsummery.detectmemory.entity.ObjWithContext;
import com.example.lawson.androidsummery.detectmemory.entity.ObjWithoutContext;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SingleOOM {

    private static SingleOOM instance;
    private Context context;
    private SoftReference<TextView> textView;
//    private TextView textView;
    private String fromActivity;
    private ObjWithContext objWithContext;
    private ObjWithoutContext objWithoutContext;

//直接在构造函数中传入context对象
//    private SingleOOM(Context context) {
//        this.context = context;
//    }
//
//    public static SingleOOM getInstance(Context context) {
//        if (instance == null) {
//            instance = new SingleOOM(context);
//        }
//        return instance;
//    }

    public static SingleOOM getInstance() {
        if (instance == null) {
            instance = new SingleOOM();
        }
        return instance;
    }

//    public void setTextView(TextView textView) {
//        this.textView = textView;
//    }

    public void setTextView(TextView textView) {
        this.textView = new SoftReference<TextView>(textView);
    }

    public void setFromActivity(String fromActivity) {
        this.fromActivity = fromActivity;
    }

    public void setObjWithContext(ObjWithContext objWithContext) {
        this.objWithContext = objWithContext;
    }

    public void setObjWithoutContext(ObjWithoutContext objWithoutContext) {
        this.objWithoutContext = objWithoutContext;
    }
}
