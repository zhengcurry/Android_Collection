package com.curry.basic.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.curry.basic.http.HttpHelper;

import org.litepal.LitePalApplication;

import retrofit2.Retrofit;

/**
 * @Desc : 基础application
 * @Author : curry
 * @Date : 2018/12/14
 * @Update : 2018/12/14
 * @Annotation :继承的LitePalApplication--是因为继承了LitePal数据库
 */
public class BaseApplication extends LitePalApplication {
    private static BaseApplication application;

    public static Activity mActivity;
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        retrofit = HttpHelper.initRetrofit();

        this.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                mActivity = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mActivity = null;
            }
        });
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public static Activity getActivity() {
        return mActivity;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
