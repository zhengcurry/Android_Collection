package com.curry.basic.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtils {
    /**
     * 获取版本号 1
     *
     * @param ctx
     * @return
     */
    public static int getLocalVersionCode(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取版本名 1.0.0
     *
     * @param ctx
     * @return
     */
    public static String getLocalVersionName(Context ctx) {
        String versionName = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
