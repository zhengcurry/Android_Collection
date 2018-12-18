package com.curry.basic.ui.main;

import com.heinqi.curry_base.mvp_base.BasePresenter;
import com.heinqi.curry_base.mvp_base.BaseView;

/**
 * @Desc : P和V的契约：关联P和V
 * @Author : curry
 * @Date : 2018/11/13
 * @Update : 2018/11/13
 */
public interface MainContract {
    interface MainPresenter extends BasePresenter {
    }

    interface MainView extends BaseView<MainPresenter> {
    }

}
