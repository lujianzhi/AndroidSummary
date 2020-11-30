package com.example.lawson.androidsummery.net.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.lawson.androidsummery.net.okhttp.MyDealBusiness;
import com.example.lawson.androidsummery.net.okhttp.OkHttpEngine;
import com.example.lawson.androidsummery.net.retrofit.News;
import com.example.lawson.androidsummery.net.retrofit.NewsParam;
import com.example.lawson.androidsummery.net.retrofit.NewsService;
import com.example.lawson.androidsummery.net.volley.BitmapCache;
import com.example.lawson.androidsummery.net.volley.UTF8StringRequest;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
        findViewById(R.id.okhttp_asyn_get).setOnClickListener(this);
        findViewById(R.id.okhttp_asyn_post).setOnClickListener(this);
        findViewById(R.id.okhttp_asyn_upload_file).setOnClickListener(this);
        findViewById(R.id.okhttp_asyn_download_file).setOnClickListener(this);
        findViewById(R.id.okhttp_asyn_cancel_request).setOnClickListener(this);
        findViewById(R.id.okhttp_asyn_get_diy_request).setOnClickListener(this);
        findViewById(R.id.retrofit_get).setOnClickListener(this);
        findViewById(R.id.retrofit_get_path).setOnClickListener(this);
        findViewById(R.id.retrofit_post_field).setOnClickListener(this);
        findViewById(R.id.retrofit_post_body).setOnClickListener(this);
        findViewById(R.id.retrofit_rxjava).setOnClickListener(this);
        contentTv = findViewById(R.id.content);
        imageSv = findViewById(R.id.image_sv);
        contentSv = findViewById(R.id.content_sv);
        image2Sv = findViewById(R.id.image2_sv);
        image = findViewById(R.id.image);
        image2 = findViewById(R.id.image2);

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

            case R.id.okhttp_asyn_get:
                okhttpAsynGet();
                break;

            case R.id.okhttp_asyn_post:
                okhttpAsynPost();
                break;

            case R.id.okhttp_asyn_upload_file:
                okhttpAsynUploadFile();
                break;

            case R.id.okhttp_asyn_download_file:
                okhttpAsynDownloadFile();
                break;

            case R.id.okhttp_asyn_cancel_request:
                okhttpAsynCancelRequest();
                break;

            case R.id.okhttp_asyn_get_diy_request:
                okhttpAsynGetDiyRequest();
                break;

            case R.id.retrofit_get:
                retrofitGet();
                break;

            case R.id.retrofit_get_path:
                retrofitGetPath();
                break;

            case R.id.retrofit_post_field:
                retrofitPostField();
                break;

            case R.id.retrofit_post_body:
                retrofitPostBody();
                break;

            case R.id.retrofit_rxjava:
                retrofitRxJava();
                break;
        }
    }

    /**
     * retrofit结合RxJava
     */
    private void retrofitRxJava() {
        String url = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())         //Retrofit到Gson进行转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //Retrofit到RxJava2进行转换
                .build();
        NewsService newsService = retrofit.create(NewsService.class);
        Map<String, String> map = new HashMap<>();
        map.put("type", "top");
        map.put("key", "a112f6137f862e6dadace2ff3489d093");
        newsService.getRxJavaGetParam(map)  //RxJava提供的工作线程
                .subscribeOn(Schedulers.io())   //指定了上游：Observable中的线程
                .observeOn(AndroidSchedulers.mainThread())  //指定了下游：subscribe接口中的线程
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(@NonNull News news) {
                        showContent(news.getResult().getData().toString()); //主线程
                    }
                });

    }

    private void retrofitPostBody() {
        String url = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsService newsService = retrofit.create(NewsService.class);
        retrofit2.Call<News> call = newsService.getPostParam(new NewsParam("top", "a112f6137f862e6dadace2ff3489d093"));
        call.enqueue(new retrofit2.Callback<News>() {
            @Override
            public void onResponse(retrofit2.Call<News> call, retrofit2.Response<News> response) {
                if (response.body() != null) {
                    if (response.body().getResult() != null) {
                        String json = response.body().getResult().getData().toString();
                        showContent(json);
                    } else {
                        showContent(response.body().getReason());
                    }
                } else {
                    showContent(response.toString());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<News> call, Throwable t) {
                showContent(t.getMessage());
            }
        });
    }

    private void retrofitPostField() {
        String url = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsService newsService = retrofit.create(NewsService.class);
        retrofit2.Call<News> call = newsService.getPostParam("top", "a112f6137f862e6dadace2ff3489d093");
        call.enqueue(new retrofit2.Callback<News>() {
            @Override
            public void onResponse(retrofit2.Call<News> call, retrofit2.Response<News> response) {
                String json = response.body().getResult().getData().toString();
                showContent(json);
            }

            @Override
            public void onFailure(retrofit2.Call<News> call, Throwable t) {
                showContent(t.getMessage());
            }
        });
    }

    private void retrofitGetPath() {
        String url = "http://v.juhe.cn/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsService newsService = retrofit.create(NewsService.class);
        retrofit2.Call<News> call = newsService.getGetParam("toutiao");
        call.enqueue(new retrofit2.Callback<News>() {
            @Override
            public void onResponse(retrofit2.Call<News> call, retrofit2.Response<News> response) {
                String json = response.body().getResult().getData().toString();
                showContent(json);
            }

            @Override
            public void onFailure(retrofit2.Call<News> call, Throwable t) {
                showContent(t.getMessage());
            }
        });

    }

    /**
     * retrofit_get
     */
    private void retrofitGet() {
        String url = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //addConverterFactory用于指定返回的参数数据类型，这里我们支持String和Gson类型。
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //用Retrofit创建接口文件
        NewsService ipService = retrofit.create(NewsService.class);
        Map<String, String> map = new HashMap<>();
        map.put("type", "top");
        map.put("key", "a112f6137f862e6dadace2ff3489d093");
        retrofit2.Call<News> call = ipService.getGetParam(map);
        call.enqueue(new retrofit2.Callback<News>() {
            @Override
            public void onResponse(retrofit2.Call<News> call, retrofit2.Response<News> response) {
                String json = response.body().getResult().getData().toString();
                showContent(json);
            }

            @Override
            public void onFailure(retrofit2.Call<News> call, Throwable t) {
                showContent(t.getMessage());
            }
        });
    }

    /**
     * okhttp_asyn_get_diy_request
     */
    private void okhttpAsynGetDiyRequest() {
        OkHttpEngine.getInstance().sendGet("http://www.baidu.com", new MyDealBusiness() {
            @Override
            public void failWorkThread(Call call, IOException e) {
                Log.i("Ian", "failWorkThread - Thread : " + Thread.currentThread().getName());

            }

            @Override
            public void failMainThread(Call call, IOException e) {
                Log.i("Ian", "failMainThread - Thread : " + Thread.currentThread().getName());
                showContent(e.getMessage());
            }

            @Override
            public void successWorkThread(Call call, okhttp3.Response response) {
                Log.i("Ian", "successWorkThread - Thread : " + Thread.currentThread().getName());

            }

            @Override
            public void successMainThread(Call call, okhttp3.Response response) {
                Log.i("Ian", "successMainThread - Thread : " + Thread.currentThread().getName());
                showContent(response.toString());
            }
        });
    }

    /**
     * okhttp_asyn_cancel_request
     */
    private void okhttpAsynCancelRequest() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
        okhttp3.Request request = new okhttp3.Request.Builder().url("http://www.baidu.com").build();
        final Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final okhttp3.Response response) {
                //不在主线程
                Log.i("Ian", "onResponse - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (call.isCanceled()) {
                            showContent("取消请求");
                        } else {
                            if (response.cacheResponse() != null) {
                                showContent("cacheResponse : " + response.cacheResponse().toString());
                            } else {
                                showContent("networkResponse : " + response.networkResponse().toString());
                            }
                        }
                    }
                });
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCall.cancel();
            }
        }, 100);
    }

    /**
     * okhttp_asyn_download_file
     */
    private void okhttpAsynDownloadFile() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
        final okhttp3.Request request = new okhttp3.Request.Builder().url("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=647672007,2716159314&fm=26&gp=0.jpg").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final okhttp3.Response response) throws IOException {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                InputStream inputStream = response.body().byteStream();
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "b.jpg");
                OutputStream outputStream = new FileOutputStream(file);
                int temp;
                while ((temp = inputStream.read()) != -1) {
                    outputStream.write(temp);
                }
                outputStream.close();
                inputStream.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(response.toString());
                    }
                });
            }
        });
    }

    /**
     * okhttp_asyn_upload_file
     */
    private void okhttpAsynUploadFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "a.jpg");
        if (!file.exists()) {
            Log.i("Ian", Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "a.jpg");
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
        okhttp3.Request request = new okhttp3.Request.Builder().url("https://github.com/lujianzhi/AndroidSummary/tree/master/upload")
                .post(RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) {
                //不在主线程
                Log.i("Ian", "onResponse - Thread : " + Thread.currentThread().getName());
                final String json = response.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(json);
                    }
                });
            }
        });
    }

    /**
     * okhttp_asyn_post
     */
    private void okhttpAsynPost() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        RequestBody requestBody = new FormBody.Builder()
                .add("type", "top")
                .add("key", "a112f6137f862e6dadace2ff3489d093")
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder().url("http://v.juhe.cn/toutiao/index")
                .post(requestBody)
                .build();
        Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                //不在主线程
                Log.i("Ian", "onResponse - Thread : " + Thread.currentThread().getName());
                final String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(json);
                    }
                });
            }
        });
    }

    /**
     * okhttp_asyn_get
     */
    private void okhttpAsynGet() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        //默认就为GET
        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder().url("http://www.baidu.com").method("GET", null);
        okhttp3.Request request = requestBuilder.build();
        Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //不在主线程
                Log.i("Ian", "onFailure - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final okhttp3.Response response) {
                //不在主线程
                Log.i("Ian", "onResponse - Thread : " + Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.cacheResponse() != null) {
                            showContent("cacheResponse : " + response.cacheResponse().toString());
                        } else {
                            showContent("networkResponse : " + response.networkResponse().toString());
                        }
                    }
                });
            }
        });
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
