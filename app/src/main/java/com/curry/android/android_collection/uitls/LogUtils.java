package com.curry.android.android_collection.uitls;

import android.util.Log;

import static com.curry.android.android_collection.constant.Constant.BASE_NAME;

/**
 * Created by curry on 2017/5/8.
 * <p>
 * when LEVEL equal to VERBOSE ,can print all log .
 * when LEVEL equal to NOTHING ,can shield all log.
 */

public class LogUtils {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;
    private static final String TAG = BASE_NAME;

    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            Log.e(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (LEVEL <= INFO) {
            Log.e(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (LEVEL <= WARN) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, msg);
        }
    }
}