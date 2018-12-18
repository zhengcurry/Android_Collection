package com.curry.basic.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * @author curry
 * @date 2017/10/9
 * @Desc 弹出框
 */
public class AlertDialogUtil {

    /**
     * 仅有确认按钮
     *
     * @param title
     * @param content
     * @param dialogClickListener
     */
    public static void showCustomDialog(String title, String content, DialogClickListener dialogClickListener) {
        showCustomDialog(0, title, content, "", "确认",
                false, false, dialogClickListener);
    }

    /**
     * 显示两个按钮
     *
     * @param flag
     * @param title
     * @param content
     * @param cancelText
     * @param confirmText
     * @param showCancelBtn
     * @param dialogClickListener
     */
    public static void showCustomDialog(int flag, String title, String content, String cancelText,
                                        String confirmText, boolean showCancelBtn,
                                        DialogClickListener dialogClickListener) {
        showCustomDialog(flag, title, content, cancelText, confirmText,
                showCancelBtn, true, dialogClickListener);
    }

    /**
     * 自定义弹出框
     *
     * @param flag                一个页面多个dialog 进行标识
     * @param title
     * @param showCancelBtn
     * @param dialogClickListener
     */
    public static void showCustomDialog(int flag, String title, String content, String cancelText,
                                        String confirmText, boolean showCancelBtn, boolean cancelable,
                                        DialogClickListener dialogClickListener) {
        /*View view = View.inflate(EsecApplication.getApplication().mActivity, R.layout.dialog_base, null);
        if (showCancelBtn) {
            view.findViewById(R.id.tv_dialog_cancel).setVisibility(View.VISIBLE);
            view.findViewById(R.id.line_view).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.tv_dialog_cancel).setVisibility(View.GONE);
            view.findViewById(R.id.line_view).setVisibility(View.GONE);
        }
        ((TextView) view.findViewById(R.id.tv_dialog_title)).setText(title);
        TextView tvContent = view.findViewById(R.id.tv_dialog_content);
        if (content.equals("")) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(content);
        }
        AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                .setView(view)
                .setCancelable(cancelable)
                .create();
//        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        setDialogSize(EsecApplication.getApplication().mActivity, dialog);
        TextView tvCancel = view.findViewById(R.id.tv_dialog_cancel);
        tvCancel.setText(cancelText);
        tvCancel.setOnClickListener(v -> {
            dialog.dismiss();
            dialogClickListener.cancelClick(flag, v);
        });
        TextView tvConfirm = view.findViewById(R.id.tv_dialog_confirm);
        tvConfirm.setText(confirmText);
        tvConfirm.setOnClickListener(v -> dialogClickListener.confirmClick(flag, dialog, v));*/
    }

    public interface DialogClickListener {
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
        try {
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
//            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(true);
            dialog.show();
            return dialog;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
