package com.heinqi.curry_base.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.heinqi.curry_base.HttpService;
import com.heinqi.curry_base.R;
import com.heinqi.curry_base.http.OkHttpUtil;
import com.heinqi.curry_base.http.ResultTO;
import com.heinqi.curry_base.utils.JsonUtil;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private MainContract.MainPresenter mPresenter;
    private String TAG = "curry";
    private HttpService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                //被观察者调用onNext 观察者将会在onNext方法中收到回调
                emitter.onNext("hello");
                //被观察者调用onComplete 观察者会执行onComplete 两者断开连接
//                emitter.onComplete();

//                emitter.onNext("hello2");
            }
        });

        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String o) {
                Log.e(TAG, "onNext: " + o);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };

        observable.subscribe(observer);

        *//**
         * 简化上面的操作
         *//*
        Observable<String> observable1 = Observable.just("hello");
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        };
        observable1.subscribe(consumer);*/


//        testZip();

        testRetrofit();
        testObservable();
    }

    private void testThread() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.e("kaelpu", "Observable thread is : " + Thread.currentThread().getName());
                Log.e("kaelpu", "emitter 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("kaelpu", "Observer thread is :" + Thread.currentThread().getName());
                Log.e("kaelpu", "onNext: " + integer);
            }
        };

        /**
         * 多次指定Observable的线程只有第一次指定的有效,
         * 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
         *
         * Schedulers:线程调度器
         */
        observable.subscribeOn(Schedulers.newThread())//在子线程发送（被观察者）
                .observeOn(AndroidSchedulers.mainThread())//主线中接收（观察者）
                .subscribe(consumer);
    }

    private void testZip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                Log.d(TAG, "emitter 1");
                emitter.onNext(1);
//                Log.d(TAG, "emitter 2");
                emitter.onNext(2);
//                Log.d(TAG, "emitter 3");
                emitter.onNext(3);
//                Log.d(TAG, "emitter 4");
                emitter.onNext(4);
//                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                Log.d(TAG, "emitter A");
                emitter.onNext("A");
//                Log.d(TAG, "emitter B");
                emitter.onNext("B");
//                Log.d(TAG, "emitter C");
                emitter.onNext("C");
//                Log.d(TAG, "emitter complete2");
                emitter.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    private String error() throws Exception {
        throw new Exception();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(@NonNull MainContract.MainPresenter presenter) {
        /**
         * lintOptions {
         *   disable 'RestrictedApi'
         * }
         */
        mPresenter = checkNotNull(presenter);
    }

    /**
     * @Desc : retrofit初始化
     * @Author : curry
     * @Date : 2018/11/14
     * @Update : 2018/11/14
     * @Annotation :retrofit
     */
    private void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                //接口
                .baseUrl("https://www.choosepower.cn/")
                //设置数据解析器--（需要添加相关依赖，例com.squareup.retrofit2:converter-gson）
                .addConverterFactory(GsonConverterFactory.create())
                //结合RxJava需要，目的在于返回值定义为Observable（需要添加相关依赖，例com.squareup.retrofit2:adapter-rxjava）
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpUtil.genericClient())
                .build();
        service = retrofit.create(HttpService.class);
    }


    private void testObservable() {
        ResourceSubscriber resourceSubscriber;
        Subscriber subscriber;

        service.testPost()
                .subscribeOn(Schedulers.io()) //发送 在子线程
                .observeOn(AndroidSchedulers.mainThread()) //接受 在主线程
                .subscribe(new Observer<ResultTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultTO resultTO) {
                        Log.e(TAG, "onNext: " + JsonUtil.toJson(resultTO));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testCall() {
        //对发送请求进行封装
        Call<String> news = service.test();
        //发送网络请求(异步)
        news.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //TODO 成功回调
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //TODO 失败操作
            }
        });
    }

}
