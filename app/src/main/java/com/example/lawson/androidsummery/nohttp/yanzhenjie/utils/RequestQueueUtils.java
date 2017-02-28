package com.example.lawson.androidsummery.nohttp.yanzhenjie.utils;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Created by Ian.Lu on 2017/1/9.
 * Project : AndroidSummary
 */

public class RequestQueueUtils {

    private static RequestQueue requestQueue;

    private RequestQueueUtils() {
    }

    public static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = NoHttp.newRequestQueue();
        }
        return requestQueue;
    }

}
