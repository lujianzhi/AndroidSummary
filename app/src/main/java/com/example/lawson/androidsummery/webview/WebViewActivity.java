package com.example.lawson.androidsummery.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private TextView title;
    private LinearLayout web_activity_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.web_view);
        title = (TextView) findViewById(R.id.title);
        web_activity_layout = (LinearLayout) findViewById(R.id.web_activity_layout);

        if (webView != null) {
            webView.loadUrl("http://blog.csdn.net/qq_24530405/article/details/52067474");
            webView.setWebViewClient(webViewClient);
            webView.setWebChromeClient(webChromeClient);
            if (Build.VERSION.SDK_INT >= 19) {
/*对系统API在19以上的版本作了兼容。因为4.4以上系统在onPageFinished时再恢复图片加载时,如果存在多张图片引用的是相同的src时，会只有一个image标签得到加载，因而对于这样的系统我们就先直接加载。*/
                webView.getSettings().setLoadsImagesAutomatically(true);
            } else {
                webView.getSettings().setLoadsImagesAutomatically(false);
            }
            webView.getSettings().setSupportZoom(true);
        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            title.setText("加载进度 : " + newProgress);
            if (newProgress == 100) {
                title.setText("加载完成");
            }
        }
    };

    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!webView.getSettings().getLoadsImagesAutomatically()) {
                webView.getSettings().setLoadsImagesAutomatically(true);
            }
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
    protected void onDestroy() {
        super.onDestroy();
        //如果网页在播放视频，不加以下代码，会出现退出activity时仍然有声音的情况
        web_activity_layout.removeView(webView);
        webView.destroy();

    }
}
