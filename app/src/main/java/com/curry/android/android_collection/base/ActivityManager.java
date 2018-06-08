package com.curry.android.android_collection.base;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * @author curry
 * @date 2018/6/6
 * @Desc activity管理类
 */

public class ActivityManager {

    private static Stack<Activity> activityStack = null;

    private static ActivityManager instance = null;

    private ActivityManager() {
    }

    /**
     * getAppManager:(获取单例)
     */
    public static ActivityManager getAppManager() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    /**
     * addActivity:(添加activity到堆栈)
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * getCurrentActivity:(获取最后一个入栈的activity)
     */
    public Activity getCurrentActivity() {
        return activityStack == null ? null : activityStack.lastElement();
    }

    /**
     * finishActivity:(结束当前activity)
     */
    public void finishActivity() {
        if (activityStack == null) {
            return;
        }
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * finishActivity:( 结束activity)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * finishActivity:(结束指定类名的Activity)
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * removeActivity:(移除某个activity)
     */
    public void removeActivity(Class<?> clz) {

        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(clz)) {
                activityStack.remove(i).finish();
                i--;
            }
        }
    }

    /**
     * removeActivity:(移除所有activity)
     */
    public void removeAllActivity() {

        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.remove(i).finish();
                i--;
            }
        }
    }

    /**
     * finishAllActivity:(结束所有Activity)
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * toHOME:(返回首页)
     */
    public void toHOME(Class<?> clz) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (!activityStack.get(i).getClass().equals(clz)) {
                activityStack.remove(i).finish();
                i--;
            }
        }
    }

    /**
     * toHOME:(返回首页)
     */
    public void toHome(Class<?> clz0, Class<?> clz1, Class<?> clz2,
                       Class<?> clz3, Class<?> clz4, Class<?> clz5) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (!activityStack.get(i).getClass().equals(clz0)
                    && !activityStack.get(i).getClass().equals(clz1)
                    && !activityStack.get(i).getClass().equals(clz2)
                    && !activityStack.get(i).getClass().equals(clz3)
                    && !activityStack.get(i).getClass().equals(clz4)
                    && !activityStack.get(i).getClass().equals(clz5)) {
                activityStack.remove(i).finish();
                i--;
            }
        }
    }

    /**
     * AppExit:(退出应用程序)
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void isMainActivity() {

    }
}