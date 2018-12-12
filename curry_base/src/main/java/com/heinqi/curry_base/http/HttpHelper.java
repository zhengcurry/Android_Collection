package com.heinqi.curry_base.http;

import com.heinqi.curry_base.base.BaseApplication;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.heinqi.curry_base.constant.Constant.BASE_URL;

/**
 * @Desc : 封装http
 * @Author : curry
 * @Date : 2018/11/26
 * @Update : 2018/11/26
 * @Annotation :封装http
 */
public class HttpHelper {

    /**
     * 初始化retrofit
     *
     * @return
     */
    public static final Retrofit initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                //接口
                .baseUrl(BASE_URL)
                //设置数据解析器--（需要添加相关依赖，例com.squareup.retrofit2:converter-gson）
                .addConverterFactory(GsonConverterFactory.create())
                //结合RxJava需要，目的在于返回值定义为Observable（需要添加相关依赖，例com.squareup.retrofit2:adapter-rxjava）
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpUtil.genericClient())
                .build();
        return retrofit;
    }

    /**
     * 创建
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T createService(Class<T> service) {
        return BaseApplication.retrofit.create(service);
    }

    /**
     * 订阅（绑定两者）
     * 被观察者Observable（发送）
     * 观察者Observer（接收）
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable
                .subscribeOn(Schedulers.io()) //发送 在子线程
                .observeOn(AndroidSchedulers.mainThread()) //接受 在主线程
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
