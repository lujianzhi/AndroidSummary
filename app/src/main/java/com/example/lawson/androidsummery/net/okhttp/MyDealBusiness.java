package com.example.lawson.androidsummery.net.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Ian.Lu on 2017/8/8.
 * Project : AndroidSummary
 */

public interface MyDealBusiness {

    void failWorkThread(Call call, IOException e);

    void failMainThread(Call call, IOException e);

    void successWorkThread(Call call, Response response);

    void successMainThread(Call call, Response response);
}
