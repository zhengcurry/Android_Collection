package com.curry.android.android_collection.test;

/**
 * @Desc : P和V的契约：关联P和V
 * @Author : curry
 * @Date : 2018/11/13
 * @Update : 2018/11/13
 */
public interface MainContract {
    interface MainPresenter extends com.heinqi.curry_base.mvp_base.BasePresenter {
    }

    interface MainView extends com.heinqi.curry_base.mvp_base.BaseView<MainPresenter> {
    }

}
