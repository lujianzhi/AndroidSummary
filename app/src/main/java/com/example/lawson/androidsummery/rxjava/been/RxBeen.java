package com.example.lawson.androidsummery.rxjava.been;

/**
 * Created by Ian.Lu on 2017/5/8.
 * Project : AndroidSummary
 */

public class RxBeen {

    private int id;
    private String content;

    public RxBeen(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RxBeen{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
