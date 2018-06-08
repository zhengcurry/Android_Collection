package com.curry.android.android_collection.uitls;

import android.content.Context;
import android.widget.Toast;

/**
 * @author curry
 * @date 2017/10/18
 * @Desc toast显示
 */

public class ToastUtil {

    public static void showToast(Context context, String message, boolean longToast) {
        Toast.makeText(context, message,
                longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
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
