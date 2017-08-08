package com.example.lawson.androidsummery.net.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.net.httpurlconnection.UrlConnManager;
import com.example.lawson.androidsummery.net.httpurlconnection.been.BasicNameValuePair;
import com.example.lawson.androidsummery.net.httpurlconnection.been.NameValuePair;
import com.example.lawson.androidsummery.net.volley.BitmapCache;
import com.example.lawson.androidsummery.net.volley.UTF8StringRequest;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView contentTv;
    private ScrollView imageSv;
    private ScrollView image2Sv;
    private ScrollView contentSv;
    private ImageView image;
    private NetworkImageView image2;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);

        requestQueue = Volley.newRequestQueue(this);

        findViewById(R.id.url_connection_post).setOnClickListener(this);
        findViewById(R.id.url_connection_get).setOnClickListener(this);
        findViewById(R.id.volley_string_request).setOnClickListener(this);
        findViewById(R.id.volley_json_request).setOnClickListener(this);
        findViewById(R.id.volley_image_request).setOnClickListener(this);
        findViewById(R.id.volley_image_loader).setOnClickListener(this);
        findViewById(R.id.volley_network_image_view).setOnClickListener(this);
        contentTv = (TextView) findViewById(R.id.content);
        imageSv = (ScrollView) findViewById(R.id.image_sv);
        contentSv = (ScrollView) findViewById(R.id.content_sv);
        image2Sv = (ScrollView) findViewById(R.id.image2_sv);
        image = (ImageView) findViewById(R.id.image);
        image2 = (NetworkImageView) findViewById(R.id.image2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.url_connection_post:
                useHttpUrlConnectionPost();
                break;

            case R.id.url_connection_get:
                useHttpUrlConnectionGet();
                break;

            case R.id.volley_string_request:
                volleyStringRequest();
                break;

            case R.id.volley_json_request:
                volleyJsonRequest();
                break;

            case R.id.volley_image_request:
                volleyImageRequest();
                break;

            case R.id.volley_image_loader:
                volleyImageLoader();
                break;

            case R.id.volley_network_image_view:
                volleyNetworkImageView();
                break;
        }
    }

    /**
     * volley_network_image_view
     */
    private void volleyNetworkImageView() {
        String url = "http://img3.duitang.com/uploads/item/201411/30/20141130000653_dLGxQ.jpeg";
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        image2.setDefaultImageResId(R.drawable.four_color);
        image2.setErrorImageResId(R.drawable.four_color);
        image2.setImageUrl(url, imageLoader);
        contentSv.setVisibility(View.GONE);
        imageSv.setVisibility(View.GONE);
        image2Sv.setVisibility(View.VISIBLE);
    }

    /**
     * volley_image_loader
     */
    private void volleyImageLoader() {
        String url = "http://img3.duitang.com/uploads/item/201411/30/20141130000653_dLGxQ.jpeg";
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(image, R.drawable.four_color, R.drawable.four_color);
        imageLoader.get(url, listener, 1000, 1000, ImageView.ScaleType.CENTER);
        contentSv.setVisibility(View.GONE);
        imageSv.setVisibility(View.VISIBLE);
        image2Sv.setVisibility(View.GONE);
    }

    /**
     * volley_image_request
     */
    private void volleyImageRequest() {
        String url = "http://img3.duitang.com/uploads/item/201411/30/20141130000653_dLGxQ.jpeg";
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                showImage(response);
            }
        }, 1000, 1000, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showContent(error.toString());
            }
        });
        requestQueue.add(imageRequest);
    }

    /**
     * volley_json_request
     */
    private void volleyJsonRequest() {
        String requestBody = "type=top&key=a112f6137f862e6dadace2ff3489d093";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://v.juhe.cn/toutiao/index", requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                showContent(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showContent(error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    /**
     * volley_string_request
     */
    private void volleyStringRequest() {
        StringRequest stringRequest = new UTF8StringRequest(Request.Method.GET, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showContent(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showContent(error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }


    /**
     * 使用
     * Get
     */
    private void useHttpUrlConnectionGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                useHttpUrlConnectionGet("http://www.baidu.com");
            }
        }.start();
    }

    /**
     * 使用
     * POST
     */
    private void useHttpUrlConnectionPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                useHttpUrlConnectionPost("http://v.juhe.cn/toutiao/index");
            }
        }.start();
    }

    /**
     * 发送请求
     * GET
     */
    private void useHttpUrlConnectionGet(String url) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = UrlConnManager.getHttpURLConnectionGet(url);

        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            final int code = httpURLConnection.getResponseCode();
            final String response = UrlConnManager.convertStreamToString(inputStream);
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showContent("code : " + code + "\n" + response);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送请求
     * POST
     */
    private void useHttpUrlConnectionPost(String url) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = UrlConnManager.getHttpURLConnectionPost(url);
        try {
            List<NameValuePair> postParams = new ArrayList<>();
            postParams.add(new BasicNameValuePair("type", "top"));
            postParams.add(new BasicNameValuePair("key", "a112f6137f862e6dadace2ff3489d093"));
            UrlConnManager.postParams(httpURLConnection.getOutputStream(), postParams);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            final int code = httpURLConnection.getResponseCode();
            final String response = UrlConnManager.convertStreamToString(inputStream);
            inputStream.close();
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showContent("code : " + code + "\n" + response);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showImage(Bitmap response) {
        image.setImageBitmap(response);
        contentSv.setVisibility(View.GONE);
        imageSv.setVisibility(View.VISIBLE);
        image2Sv.setVisibility(View.GONE);
    }

    private void showContent(String str) {
        contentTv.setText(str);
        contentSv.setVisibility(View.VISIBLE);
        imageSv.setVisibility(View.GONE);
        image2Sv.setVisibility(View.GONE);
    }

}
