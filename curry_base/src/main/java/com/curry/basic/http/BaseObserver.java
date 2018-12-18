package com.curry.basic.http;

import android.content.Context;

import com.curry.basic.utils.EasyToastUtil;
import com.curry.basic.utils.NetWorkUtils;

import org.json.JSONException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    private boolean mNeedShowDialog = true;
    private ProgressHandler mProgressHandler;
    private Context context;

    public BaseObserver(Context context) {
        this.mProgressHandler = new ProgressHandler(context, true);
        this.context = context;
    }

    public BaseObserver(Context context, boolean dialogShow) {
        this.mNeedShowDialog = dialogShow;
        this.context = context;
        this.mProgressHandler = new ProgressHandler(context, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!NetWorkUtils.checkNetworkAvailable()) {
            EasyToastUtil.showToast("当前网络不可用，请检查网络情况");
            onComplete();
            return;
        }
        if (mNeedShowDialog) {
            mProgressHandler.showProgressDialog();
        }
    }

    @Override
    public void onNext(T o) {
        try {
            if (o instanceof ResultTO && ((ResultTO) o).getStatus() == 401) {
                EasyToastUtil.showToast("401异常");
                return;
            }
            onSuccess(o);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
        mProgressHandler.disMissProgressDialog();
    }

    @Override
    public void onComplete() {
        mProgressHandler.disMissProgressDialog();
    }


    public abstract void onSuccess(T o) throws JSONException;

    public abstract void onFailed(Throwable e);
}
