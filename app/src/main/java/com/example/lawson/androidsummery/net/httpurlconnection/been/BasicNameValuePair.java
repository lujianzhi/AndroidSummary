package com.example.lawson.androidsummery.net.httpurlconnection.been;

/**
 * Created by Ian.Lu on 2017/7/25.
 * Project : AndroidSummary
 */

public class BasicNameValuePair implements NameValuePair {

    private String name;
    private String value;

    public BasicNameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}