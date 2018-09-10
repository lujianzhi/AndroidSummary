package com.example.lawson.androidsummery.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Ian.Lu on 2018/6/27.
 * Project : AndroidSummary
 */
public class AndroidToJs {

    @JavascriptInterface
    public void jsMethod(String content) {
        Toast.makeText(mContext, "content : " + content, Toast.LENGTH_SHORT).show();
    }

    private Context mContext;

    public AndroidToJs(Context context) {
        mContext = context;
    }

}
