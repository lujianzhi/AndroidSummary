package com.example.lawson.androidsummery.beibei.hbaction;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian.Lu on 2018/11/7.
 * Project : AndroidSummary
 */
public class Bundle {

    private static final String BUNDLE_JSON_FILE_NAME = "bundles.json";
    private static final String BUNDLE_JSON_VERSION_KEY = "version";
    private static final String BUNDLE_JSON_BUNDLES_KEY = "bundles";
    private static final String BUNDLE_JSON_BUNDLE_NAME_KEY = "name";
    private static final String BUNDLE_JSON_BUNDLE_URI_KEY = "uri";
    private static final String BUNDLE_JSON_BUNDLE_PACKAGE_KEY = "package";
    private static final int BUNDLE_JSON_VERSION = 1;

    private static List<Bundle> mBundleList;

    private String mName;
    private String mUri;
    private String mPackage;
    private IAction mAction;

    public Bundle(JSONObject bundleObj) {
        try {
            mName = bundleObj.getString(BUNDLE_JSON_BUNDLE_NAME_KEY);
            mUri = bundleObj.getString(BUNDLE_JSON_BUNDLE_URI_KEY);
            mPackage = bundleObj.getString(BUNDLE_JSON_BUNDLE_PACKAGE_KEY);
            getAction();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getAction() {
        try {
            Class actionClass = Class.forName(mPackage + "." + mName);
            mAction = (IAction) actionClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void loadJson() {
        Context context = ActionManager.getContext();

        try {
            InputStream inputStream = context.getAssets().open(BUNDLE_JSON_FILE_NAME);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();

            String actionJson = new String(data, 0, size);
            loadJsonFile(context, actionJson);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Ian", "解析出错");
        }
    }

    private static void loadJsonFile(Context context, String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int version = jsonObject.getInt(BUNDLE_JSON_VERSION_KEY);
            if (version == BUNDLE_JSON_VERSION) {
                loadBundles(jsonObject.getJSONArray(BUNDLE_JSON_BUNDLES_KEY));
            } else {
                Toast.makeText(context, "版本号不支持", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void loadBundles(JSONArray bundleJsonArray) {
        if (bundleJsonArray == null) {
            return;
        }
        List<Bundle> bundleList = new ArrayList<>(bundleJsonArray.length());
        for (int i = 0, length = bundleJsonArray.length(); i < length; i++) {
            try {
                JSONObject bundleObj = bundleJsonArray.getJSONObject(i);
                Bundle bundle = new Bundle(bundleObj);
                bundleList.add(bundle);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mBundleList = bundleList;
    }

    public static Bundle getSpecialBundle(String uri) {
        for (Bundle bundle : mBundleList) {
            if (uri.equals(bundle.mUri)) {
                return bundle;
            }
        }
        return null;
    }

    public Object runAction() {
        return mAction.run();
    }
}
