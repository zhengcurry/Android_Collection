package com.heinqi.curry_base.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.heinqi.curry_base.R;

/**
 * Created by curry on 2018/6/21.
 */

public class ProgressHandler extends Handler {
    public static final int SHOW_PROGRESS = 0;
    public static final int DISMISS_PROGRESS = 1;
    private ProgressDialog pd;
    private Context mContext;
    private ProgressCancelListener mProgressCancelListener;
    private boolean cancelable;

    public ProgressHandler(Context context, boolean cancelable) {
        this.mContext = context;
        this.cancelable = cancelable;
    }

    public ProgressHandler(Context context, ProgressCancelListener listener, boolean cancelable) {
        this.mContext = context;
        mProgressCancelListener = listener;
        this.cancelable = cancelable;
    }

    public void initProgressDialog(String message) {
        if (pd == null) {
            pd = new ProgressDialog(mContext);
            pd.setCancelable(cancelable);
            pd.setMessage(message);
            pd.setCanceledOnTouchOutside(false);
            pd.setOnKeyListener(onKeyListener);
            if (cancelable) {
                pd.setOnCancelListener(dialog -> {
                    if (mProgressCancelListener != null) {
                        mProgressCancelListener.onProgressCanceled();
                    }
                });
            }
            if (!pd.isShowing()) {
                pd.show();//显示进度条
            }
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();//取消进度条
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case SHOW_PROGRESS:
                initProgressDialog(msg.getData().getString("message"));
                break;
            case DISMISS_PROGRESS:
                dismissProgressDialog();
                break;
        }
    }


    public static DialogInterface.OnKeyListener onKeyListener = (dialog, keyCode, event) -> {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            dismissDialog();
//        }
        return false;
    };

    //接口，用来取消进度条
    public interface ProgressCancelListener {
        void onProgressCanceled();
    }

//    private ProgressHandler mProgressHandler = new ProgressHandler(context, this, true);

    public void showProgressDialog() {
        Bundle bundle = new Bundle();
        bundle.putString("message", "正在加载...");
        Message message = obtainMessage(ProgressHandler.SHOW_PROGRESS);
        message.setData(bundle);
        message.sendToTarget();
    }

    public void disMissProgressDialog() {
        obtainMessage(ProgressHandler.DISMISS_PROGRESS).sendToTarget();
    }
}
