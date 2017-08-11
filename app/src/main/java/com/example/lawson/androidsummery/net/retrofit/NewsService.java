package com.example.lawson.androidsummery.net.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Ian.Lu on 2017/8/10.
 * Project : AndroidSummary
 */

public interface NewsService {
    //Retrofit提供的请求方式注解有@GET和@POST等，分别代表GET请求和POST请求，我们在这里访问的界面是“getIpInfo.php”
    @GET("index")
    //参数注解有@PATH和@Query、@QueryMap等，@Query就是我们的请求的键值对的设置，在这里@Query(“ip”)代表键，“String ip”则代表值。
    //但是在网络请求中一般为了更精确的查找到我们所需要的数据，需要传入很多的查询参数，如果用@Query会比较麻烦，这时我们可以采用@QueryMap，将所有的参数集成在一个Map统一传递
    Call<News> getGetParam(@QueryMap() Map<String, String> map);

    //在GET注解中包含了{path}，它对应着@Path注解中的”path”，而用来替换{path}的正是需要传入的 “String path”的值
    @GET("{path}/index?type=top&key=a112f6137f862e6dadace2ff3489d093")
    Call<News> getGetParam(@Path("path") String path);

    //一定要声明FormUrlEncoded来标明这是一个表单请求，否则会报错
    //java.lang.IllegalArgumentException: @Field parameters can only be used with form encoding. (parameter #1)
    @FormUrlEncoded
    @POST("index")
    Call<News> getPostParam(@Field("type") String type, @Field("key") String key);

    @POST("index")
        //用@Body这个注解标识参数对象，retrofit会将Ip对象转换为字符串
    Call<News> getPostParam(@Body NewsParam newsParam);
}
