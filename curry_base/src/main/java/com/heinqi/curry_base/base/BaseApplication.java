package com.heinqi.curry_base.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.heinqi.curry_base.http.HttpHelper;
import com.heinqi.curry_base.utils.LogUtils;

import retrofit2.Retrofit;

public class BaseApplication extends MultiDexApplication {
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
}
