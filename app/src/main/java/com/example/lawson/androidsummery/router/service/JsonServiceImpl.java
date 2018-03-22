package com.example.lawson.androidsummery.router.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Ian.Lu on 2018/3/22.
 * Project : AndroidSummary
 */

@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {
    private Gson gson;

    @Override
    public <T> T json2Object(String s, Class<T> aClass) {
        return gson.fromJson(s, aClass);
    }

    @Override
    public String object2Json(Object o) {
        return gson.toJson(o);
    }

    @Override
    public <T> T parseObject(String s, Type type) {
        return gson.fromJson(s, type);
    }

    @Override
    public void init(Context context) {
        gson = new Gson();
    }
}
