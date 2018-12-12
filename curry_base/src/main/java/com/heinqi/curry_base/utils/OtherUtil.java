package com.heinqi.curry_base.utils;

/**
 * Created by curry on 2017/7/8.
 * 其他工具
 */

public class OtherUtil {
    /**
     * 防止多次点击  在按钮的点击事件中调用
     */
    private static long lastClickTime = 0;//上次点击的时间

    public static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();//当前系统时间
        boolean isAllowClick;//是否允许点击
        if (currentTime - lastClickTime > 2000) {
            isAllowClick = false;
        } else {
            isAllowClick = true;
        }
        lastClickTime = currentTime;
        return isAllowClick;
    }
}
