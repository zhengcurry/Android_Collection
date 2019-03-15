package com.curry.basic.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * 今日头条方案
 * Android适配：修改设备密度
 */
public class DisplayUtil {
    //UI提供的设计图尺寸
    private static final int UI_MEASURE = 360;

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        //获取DisplayMetrics对象
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            //获取density密度
            sNoncompatDensity = appDisplayMetrics.density;
            //获取scaledDensity--字体的缩放因子
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            // 防止系统切换后不起作用--如果在系统设置中切换字体，再返回应用，字体并没有变化
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        float targetDensity = appDisplayMetrics.widthPixels / UI_MEASURE;
        // 防止字体变小
        // 某些用户在系统中修改了字体大小失效了，但是不能直接用原始的scaledDensity，直接用的话可能导致某些文字超过显示区域
        float targetScaleDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        /**
         * 设置application中的值
         */
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        /**
         * 设置activity中的值
         */
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;

    }
}
