package com.example.lawson.androidsummery.net.retrofit;

/**
 * Created by Ian.Lu on 2017/8/11.
 * Project : AndroidSummary
 */

public class NewsParam {
    private String type;

    private String key;

    public NewsParam(String type, String key) {
        this.type = type;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
