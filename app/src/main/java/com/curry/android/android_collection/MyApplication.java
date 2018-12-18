package com.curry.android.android_collection;


import com.curry.basic.base.BaseApplication;

public class MyApplication extends BaseApplication {

//    public Activity mActivity;
    private static MyApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initJPush();

        /*******activity生命周期*******//*
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mActivity = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });*/
    }

//    public static HfamsApplication getApplication() {
//        return application;
//    }

    /**
     * 极光推送初始化
     */
    private void initJPush() {
//        JPushInterface.setDebugMode(false);
//        JPushInterface.init(this);
    }
}
