package com.example.lawson.androidsummery.net.httpurlconnection;

import android.text.TextUtils;

import com.example.lawson.androidsummery.net.httpurlconnection.been.NameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Ian.Lu on 2017/7/25.
 * Project : AndroidSummary
 */

public class UrlConnManager {

    /**
     * 创建HttpURLConnection
     * POST
     */
    public static HttpURLConnection getHttpURLConnectionPost(String url) {
        HttpURLConnection httpURLConnection = null;

        try {
            URL mUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) mUrl.openConnection();

            //设置连接超时时间
            httpURLConnection.setConnectTimeout(15000);
            //设置读取超时时间
            httpURLConnection.setReadTimeout(15000);
            //设置请求方法
            httpURLConnection.setRequestMethod("POST");
            //添加Header
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            //接受输入流
            httpURLConnection.setDoInput(true);
            //传递参数时需要开启
            httpURLConnection.setDoOutput(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpURLConnection;
    }

    /**
     * 创建HttpURLConnection
     * GET
     */
    public static HttpURLConnection getHttpURLConnectionGet(String url) {
        HttpURLConnection httpURLConnection = null;

        try {
            URL mUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) mUrl.openConnection();

            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setDoInput(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpURLConnection;
    }

    /**
     * 将请求参数写入到输出流中
     */
    public static void postParams(OutputStream outputStream, List<NameValuePair> paramList) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : paramList) {
            if (!TextUtils.isEmpty(stringBuilder)) {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(nameValuePair.getName(), "UTF-8"));
            stringBuilder.append("&");
            stringBuilder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        writer.write(stringBuilder.toString());
        writer.flush();
        writer.close();
    }

    /**
     * 请求结果转换成String类型
     */
    public static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

}
