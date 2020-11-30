package com.example.lawson.androidsummery.nohttp.yanzhenjie;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.nohttp.yanzhenjie.utils.RequestQueueUtils;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

public class OriginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String URL = "http://japi.juhe.cn/joke/content/list.from?sort=asc&page=1&pagesize=20&time=1418816972&key=be2fabe9f3bd263b305ebd03576a9636";
    private final int STRING_WHAT = 1;

    private Button string_request;

    private OnResponseListener<String> stringOnResponseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin);

        initData();

        initView();

    }

    private void initView() {
        string_request = findViewById(R.id.string_request);
        string_request.setOnClickListener(this);
    }

    private void initData() {
        stringOnResponseListener = new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                dealOnStart(what);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                dealOnSucceed(what, response);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                dealOnFailed(what, response);
            }

            @Override
            public void onFinish(int what) {
                dealOnFinish(what);
            }
        };
    }

    private void getStringRequest() {
        Request<String> request = NoHttp.createStringRequest(URL, RequestMethod.GET);
        RequestQueueUtils.getRequestQueue().add(STRING_WHAT, request, stringOnResponseListener);
    }

    private void dealOnStart(int what) {
        switch (what) {
            case STRING_WHAT:
                onStartStringRequest();
                break;
            default:
                break;
        }
    }

    private void dealOnSucceed(int what, Response<String> response) {
        switch (what) {
            case STRING_WHAT:
                Logger.i(response);
                break;
            default:
                break;
        }
    }

    private void dealOnFailed(int what, Response<String> response) {
        switch (what) {
            case STRING_WHAT:
                Logger.e(response);
                break;
            default:
                break;
        }
    }

    private void dealOnFinish(int what) {
        switch (what) {
            case STRING_WHAT:
                onFinishStringRequest();
                break;
            default:
                break;
        }
    }

    private void onStartStringRequest() {
        Toast.makeText(getBaseContext(), "onStartStringRequest", Toast.LENGTH_SHORT).show();
    }

    private void onFinishStringRequest() {
        Toast.makeText(getBaseContext(), "onFinishStringRequest", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.string_request:
                getStringRequest();
                break;

            default:
                break;
        }
    }
}
