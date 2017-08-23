package com.example.lawson.androidsummery.mvp.presenter;

import com.example.lawson.androidsummery.mvp.callback.BaseImpl;
import com.example.lawson.androidsummery.mvp.model.NewsModel;
import com.example.lawson.androidsummery.mvp.view.NewsActivity;
import com.example.lawson.androidsummery.net.retrofit.News;

import io.reactivex.annotations.NonNull;

/**
 * Created by Ian.Lu on 2017/8/18.
 * Project : AndroidSummary
 */

public class NewsPresenter extends BasePresenter<NewsActivity> {

    public NewsPresenter(NewsActivity newsActivity) {
        attachViews(newsActivity);
    }

    public void getNews(BaseImpl baseImpl) {
        String url = "http://v.juhe.cn/toutiao/";
        NewsModel.getInstance(url).execute(new IanObserver<News>(baseImpl) {
            @Override
            protected void mySuccess(@NonNull News obj) {
                getViews().setTvContentView(obj.getResult().getData().toString());
            }
        });
    }
}
