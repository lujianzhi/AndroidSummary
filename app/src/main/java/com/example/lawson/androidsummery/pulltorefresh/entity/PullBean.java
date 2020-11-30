package com.example.lawson.androidsummery.pulltorefresh.entity;


import androidx.core.util.Pools;

/**
 * Created by Ian.Lu on 2016/10/21.
 */

public class PullBean {
    //对象池
    private static final Pools.SynchronizedPool<PullBean> PULL_BEAN_POLL = new Pools.SynchronizedPool<PullBean>(10);

    private String title;
    private String content;

    public PullBean() {
    }

    public PullBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static PullBean obtain(String title, String content) {
        PullBean pb = PULL_BEAN_POLL.acquire();
        if (pb == null) {
            pb = new PullBean(title, content);
        }
        return pb;
    }

    public void recycle() {
        PULL_BEAN_POLL.release(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PullBean pullBean = (PullBean) o;

        if (title != null ? !title.equals(pullBean.title) : pullBean.title != null) return false;
        return content != null ? content.equals(pullBean.content) : pullBean.content == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
