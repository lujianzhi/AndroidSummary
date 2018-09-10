package com.example.lawson.androidsummery.webview;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import com.example.lawson.androidsummery.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout webViewContainer;
    private WebView webView;
    private TextView mTitle, preview_page, next_page, enter, call_js;
    private EditText url_et;
    private LinearLayout web_activity_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webViewContainer = (FrameLayout) findViewById(R.id.web_view_container);
        webView = new WebView(getApplicationContext());
        webViewContainer.addView(webView);
        mTitle = (TextView) findViewById(R.id.title);
        preview_page = (TextView) findViewById(R.id.preview_page);
        next_page = (TextView) findViewById(R.id.next_page);
        enter = (TextView) findViewById(R.id.enter);
        call_js = (TextView) findViewById(R.id.call_js);
        url_et = (EditText) findViewById(R.id.url_et);
        web_activity_layout = (LinearLayout) findViewById(R.id.web_activity_layout);

        initWebView();

        preview_page.setOnClickListener(this);
        next_page.setOnClickListener(this);
        enter.setOnClickListener(this);
        call_js.setOnClickListener(this);
    }

    private String url;

    private void initWebView() {
        url = "https://www.baidu.com";
        webView.loadUrl(url);
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
        if (Build.VERSION.SDK_INT >= 19) {
                /*对系统API在19以上的版本作了兼容。
                 因为4.4以上系统在onPageFinished时再恢复图片加载时,如果存在多张图片引用的是相同的src时，会只有一个image标签得到加载，
                 因而对于这样的系统我们就先直接加载。*/
            webView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            webView.getSettings().setLoadsImagesAutomatically(false);
        }

        //设置webview推荐使用的窗口，使html界面自适应屏幕
        webView.getSettings().setUseWideViewPort(true);
        //缩放至屏幕的大小
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.addJavascriptInterface(new AndroidToJs(this), "ian");

        webViewSafety(url);

        webViewCache();

        //HTTP HTTPS混用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

    }

    /**
     * 设置缓存机制，有网络时不用缓存，没网时用本地
     */
    private void webViewCache() {
        /*
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        if (NetUtils.isNetworkConnected(getApplicationContext())) {
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            url = "file:///" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/ian/html/index.html";
            webView.loadUrl(url);
            webView.getSettings().setAppCachePath(url);
        }
    }

    /**
     * 安全性
     */
    private void webViewSafety(String url) {
        //通过调用该方法删除接口
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");

        //密码明文存储漏洞
        webView.getSettings().setSavePassword(false);

        //防止其他应用使用更该WebView时，传入带有恶意file代码的文件让WebView加载
        if (url.startsWith("file://")) {
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setJavaScriptEnabled(false);
        } else {
            webView.getSettings().setAllowFileAccess(false);
            webView.getSettings().setJavaScriptEnabled(true);
        }

        //不允许 类似file:// 开头的url地址访问
        webView.getSettings().setAllowFileAccessFromFileURLs(false);

        //设置是否允许通过 file url 加载的 Javascript 可以访问其他的源(包括http、https等源)
        webView.getSettings().setAllowUniversalAccessFromFileURLs(false);

        // 设置是否允许 WebView 使用 JavaScript（默认是不允许）
        // 但很多应用（包括移动浏览器）为了让 WebView 执行 http 协议中的 JavaScript，都会主动设置为true，不区别对待是非常危险的。
        if (url.startsWith("file://")) {
            webView.getSettings().setJavaScriptEnabled(false);
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mTitle.setText("加载进度 : " + newProgress);
            if (newProgress == 100) {
                mTitle.setText("加载完成");
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            url_et.setHint(title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(WebViewActivity.this)
                    .setTitle("onJsAlert")
                    .setMessage(message)
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //执行alert的确定操作
                            result.confirm();
                        }
                    })
                    .setCancelable(false)
                    .show();
            //客户端自己手动处理
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(WebViewActivity.this)
                    .setTitle("JsConfirm")
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    })
                    .setCancelable(false)
                    .show();
            //客户端自己手动处理
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
            final EditText et = new EditText(WebViewActivity.this);
            et.setText(defaultValue);
            new AlertDialog.Builder(WebViewActivity.this)
                    .setTitle(message)
                    .setView(et)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm(et.getText().toString());
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    })
                    .setCancelable(false)
                    .show();
            //客户端自己手动处理
            return true;
        }
    };

    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mTitle.setTextColor(getResources().getColor(R.color.color_1));
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mTitle.setTextColor(getResources().getColor(R.color.color_2));
            if (!webView.getSettings().getLoadsImagesAutomatically()) {
                webView.getSettings().setLoadsImagesAutomatically(true);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri = Uri.parse(url);
            if (uri.getScheme().equals("ian")) {
                if (uri.getAuthority().equals("function")) {
                    Map<String, Object> objectMap = new HashMap<>();
                    Set<String> parameterNames = uri.getQueryParameterNames();
                    for (String name : parameterNames) {
                        objectMap.put(name, uri.getQueryParameter(name));
                    }
                    //js方法带参数时，需要用''把参数包起来
                    webView.evaluateJavascript("javascript:displayPerson('" + objectMap.values().toArray()[0] + "','" + objectMap.values().toArray()[1] + "')"
                            , new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String value) {
                                    Toast.makeText(WebViewActivity.this, "调用了javascript:displayPerson(name,age)", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                return true;
            }

            //HTTP与HTTPS混用
            {
                //返回true，并且不写view.loadUrl(url);时，WebView无法显示网页
//            view.loadUrl(url);
                //返回true时，宿主app不用当前的WebView，可以自己用其他的WebView进行处理
                //这里很奇怪的是，http与https混用，如果我调用了view.loadUrl(url)并且返回了true，会出现异常，只有返回false才正常
                return super.shouldOverrideUrlLoading(view, url);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //返回true时，宿主app不用当前的WebView，可以自己用其他的WebView进行处理
            //这里的super执行了shouldOverrideUrlLoading(view, request.getUrl().toString())
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.i("Ian", "url : " + url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Toast.makeText(WebViewActivity.this, "error : " + error.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            Log.i("Ian", "url : " + url);
            // 步骤1:判断拦截资源的条件，即判断url里的图片资源的文件名
            if (url.contains("plus_logo.png")) {
                //百度加载时的logo url:https://m.baidu.com/static/index/plus/plus_logo.png
                try {
                    //获得需要替换的资源(存放在assets文件夹里)
                    InputStream is = getApplicationContext().getAssets().open("img.jpg");
                    //替换
                    WebResourceResponse resourceResponse = new WebResourceResponse("image/jpg", "utf-8", is);
                    return resourceResponse;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return super.shouldInterceptRequest(view, url);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadUrl(null);
            webView.clearHistory();

            //如果网页在播放视频，不加以下代码，会出现退出activity时仍然有声音的情况
            web_activity_layout.removeView(webView);
            webView.destroy();

            webView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_page:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.preview_page:
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                break;
            case R.id.enter:
                webView.loadUrl(url_et.getText().toString());
                break;
            case R.id.call_js:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    webView.loadUrl("javascript:showAlert()");
                } else {
                    //js方法带参数时，需要用''把参数包起来
                    webView.evaluateJavascript("javascript:returnValue()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //此处为 js 返回的结果
                            Toast.makeText(WebViewActivity.this, "value : " + value, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }
}
