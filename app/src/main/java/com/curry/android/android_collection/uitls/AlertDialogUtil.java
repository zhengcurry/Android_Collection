package com.curry.android.android_collection.uitls;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.curry.android.android_collection.R;

/**
 * @author curry
 * @date 2017/10/9
 * @Desc 弹出框
 */
public class AlertDialogUtil {

    /**
     * 自定义弹出框
     *
     * @param flag          标识
     * @param activity
     * @param title
     * @param showCancelBtn
     * @param dialogClick
     */
    public static void showCustomDialog(int flag, Activity activity, String title, String confirmText,
                                        boolean showCancelBtn, DialogClick dialogClick) {
        View view = View.inflate(activity, R.layout.dialog_base, null);
        if (showCancelBtn) {
            view.findViewById(R.id.layout_cancel).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.layout_cancel).setVisibility(View.GONE);
        }
        ((TextView) view.findViewById(R.id.tv_dialog_title)).setText(title);
        AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                .setView(view)
                .setCancelable(true)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        setDialogSize(activity, dialog);
        view.findViewById(R.id.text_cancel).setOnClickListener(v -> {
            dialog.dismiss();
            dialogClick.cancelClick(flag, v);
        });
        TextView tvConfirm = view.findViewById(R.id.tv_dialog_confirm);
        tvConfirm.setText(confirmText);
        tvConfirm.setOnClickListener(v -> dialogClick.confirmClick(flag, dialog, v));
    }

    public interface DialogClick {
        void cancelClick(int flag, View view);

        void confirmClick(int flag, AlertDialog dialog, View view);
    }

    /**
     * 弹出一个Prompt---原生
     *
     * @param view
     * @param title
     * @param okListener
     * @param cancelListener
     */
    public static AlertDialog showPrompt(View view,
                                         String title,
                                         DialogInterface.OnClickListener okListener,
                                         DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                .setTitle(title)
                .setView(view);
        if (okListener != null) {
            builder.setPositiveButton("确定", okListener);
        }
        if (okListener != null) {
            builder.setNegativeButton("取消", cancelListener);
        }
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }

    /**
     * 设置弹出框大小
     *
     * @param activity
     * @param dialog
     */
    public static void setDialogSize(Activity activity, AlertDialog dialog) {
        Display display = activity.getWindowManager().getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        layoutParams.width = (int) (width * 0.8);
        if (layoutParams.height > (int) (width * 0.9)) {//大于才设置
            layoutParams.height = (int) (width * 0.9);
        }
        dialog.getWindow().setAttributes(layoutParams); //设置生效
    }
}
