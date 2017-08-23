package com.example.lawson.androidsummery.mvp.model.net;

import com.example.lawson.androidsummery.net.retrofit.News;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Ian.Lu on 2017/8/18.
 * Project : AndroidSummary
 */

public interface NetApi {
    @GET("index")
    Observable<News> getNewsParam(@QueryMap() Map<String, Object> map);
}
