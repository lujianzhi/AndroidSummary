package com.example.lawson.androidsummery.detectmemory.entity;

import android.content.Context;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ObjWithContext {

    private Context context;
    private String content;

    public ObjWithContext(Context context, String content) {
        this.context = context;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObjWithContext(Context context) {
        this.context = context;
    }
}
