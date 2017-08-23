package com.example.lawson.androidsummery.mvp.model;

import com.example.lawson.androidsummery.net.retrofit.News;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by Ian.Lu on 2017/8/18.
 * Project : AndroidSummary
 */

public class NewsModel extends BaseModel {

    private static NewsModel instance;

    public static NewsModel getInstance(String url) {
        if (instance == null) {
            instance = new NewsModel(url);
        }
        return instance;
    }

    private NewsModel(String url) {
        super(url);
    }

    public void execute(Observer<News> observer) {
        addParams("type", "top");
        addParams("key", "a112f6137f862e6dadace2ff3489d093");
        Observable<News> newsObservable = netApi.getNewsParam(paramsMap);
        subscribe(newsObservable, observer);
    }
}
