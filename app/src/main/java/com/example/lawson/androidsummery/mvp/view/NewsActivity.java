package com.example.lawson.androidsummery.mvp.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.mvp.presenter.NewsPresenter;

public class NewsActivity extends BaseActivity implements LoginView {

    private TextView contentTv;
    private NewsPresenter presenter;

    @Override
    int getLayoutResId() {
        return R.layout.activity_mvp;
    }

    @Override
    void initViews() {
        contentTv = findViewById(R.id.content);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void setTvContentView(String text) {
        contentTv.setText(text);
    }

    @Override
    public void attachPresenter() {
        presenter = new NewsPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                presenter.getNews(this);
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
