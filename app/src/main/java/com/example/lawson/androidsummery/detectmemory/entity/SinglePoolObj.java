package com.example.lawson.androidsummery.detectmemory.entity;


import androidx.core.util.Pools;

public class SinglePoolObj {
    private static final Pools.SynchronizedPool<SinglePoolObj> MY_OBJ_POOL = new Pools.SynchronizedPool<>(10);

    private String content;

    public SinglePoolObj(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static SinglePoolObj obtain(String content) {
        SinglePoolObj obj = MY_OBJ_POOL.acquire();
        if (obj == null) {
            obj = new SinglePoolObj(content);
        }
        return obj;
    }

    public void recycle() {
        MY_OBJ_POOL.release(this);
    }

}
