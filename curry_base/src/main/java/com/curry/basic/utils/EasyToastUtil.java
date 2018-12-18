package com.curry.basic.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.curry.basic.base.BaseApplication;

/**
 * @author curry
 * @date 2017/10/18
 * @Desc toast显示
 */

public class EasyToastUtil {

    public static void showToast(Context context, String message, boolean longToast) {
        Toast toast = Toast.makeText(context, message,
                longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(String message) {
        showToast(BaseApplication.getApplication(), message, false);
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, false);
    }

    /**
     * @param context
     * @param resId   ---资源id
     */
    public static void showToastById(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }
}
