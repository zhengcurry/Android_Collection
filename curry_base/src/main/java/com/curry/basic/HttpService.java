package com.curry.basic;

import com.curry.basic.http.ResultTO;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Desc : 接口示例
 * @Author : curry
 * @Date : 2018/11/14
 * @Update : 2018/11/14
 * @Annotation :
 * 注意请求头：
 * 一个是Headers（放在外面）；作用于方法
 * 一个是Header（放在参数中）；作用于方法的参数，可以添加不固定的请求头
 */
public interface HttpService {

    @Headers("header:value")
    @POST("")
    Call<String> test();//不与RxJava一起使用


    /**
     * 与RxJava一起使用
     */
    //test?test=
    @GET("test")
    Observable<String> testGet(@Query("test") String test);

    @GET("test")
    Observable<String> testGetMap(@QueryMap HashMap<String, String> test);

    //test/test
    @GET("test/" + "{testPath}")
    Observable<String> testPath(@Header("Authorization") String authorization,
                                @Path("testPath") String testPath);

    @POST("esec-frontend/v1/commons/area/tree")
    Observable<ResultTO> testPost();

    @POST("test")
    Observable<String> testPost(@Body RequestBody requestBody);

    //PDF文件Retrofit下载.大文件一定要加@Streaming
    //@Url : 动态添加地址
    @Streaming
    @GET
    Observable<ResponseBody> testDownLoad(@Url String fileUrl);
}
