package com.curry.basic.base;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.curry.basic.utils.EasyToastUtil;
import com.curry.basic.utils.GlobalSharedPreferences;
import com.curry.basic.utils.MyContextWrapper;
import com.gyf.barlibrary.ImmersionBar;
import com.heinqi.curry_base.R;
import java.util.Locale;


/**
 * @Desc : 基类
 * @Author : curry
 * @Date : 2018/11/15
 * @Update : 2018/11/15
 * @Annotation :仍需完善
 */

public abstract class BaseActivity extends AppCompatActivity {
    private TextView mToolbarTitle, rightTextView;
    public Toolbar mToolbar;
    private int backIconId = 0;

    //点击返回键时间
    private long mLastClickBackTime = 0;
    //是否需要点击两次返回退出，默认不需要
    private boolean mDoubleFinish = false;

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    public void setBackIconId(int id) {
        this.backIconId = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        /*****强制为竖屏******/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();

        /***三方沉浸式***/
        if (mToolbar != null) {
            ImmersionBar.with(this)
                    .titleBar(R.id.toolbar)
                    .navigationBarColor(R.color.colorPrimary)
                    .statusBarDarkFont(true, 0.2f)
                    .keyboardEnable(false)
                    .init();
        } else {
            ImmersionBar.with(this)
                    .statusBarColor(R.color.color_white)
                    .statusBarDarkFont(true, 0.2f)
                    .fitsSystemWindows(true)
                    .keyboardEnable(false)
                    .init();
        }

        initData();
        initView();
    }


    /**
     * 初始化toolbar
     */
    private void init() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void setToolbarBackGround() {
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
    }

    public void setTitleColor(int colorId) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setTextColor(ContextCompat.getColor(this, colorId));
        }
    }


    /**
     * 判断是否有Toolbar,并默认显示返回按钮
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    /**
     * 设置头部标题
     *
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar_menu);方法后面加入
        getToolbar().setNavigationIcon(backIconId == 0 ? R.mipmap.back_w_icon : backIconId);
        navigationOnClick();
    }

    public void navigationOnClick() {
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }


    /**
     * 打开一个页面,不关闭当前页面
     *
     * @param activityClass
     */
    public void startActivity(Class<? extends Activity> activityClass) {
        startActivity(activityClass, false);
    }

    /**
     * 打开一个页面
     * 并且设置成是否关闭当前页面
     *
     * @param activityClass
     * @param finishSelf
     */
    public void startActivity(Class<? extends Activity> activityClass, boolean finishSelf) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
        if (finishSelf) {
            finish();
        }
    }

    /**
     * 设置toolbar右侧按钮显示
     *
     * @param resId
     * @param onClickListener
     */
    public void setRightImageShow(int resId, View.OnClickListener onClickListener) {
        ImageView rightImageView = (ImageView) findViewById(R.id.img_right);
        rightImageView.setVisibility(View.VISIBLE);
        rightImageView.setImageResource(resId);
        rightImageView.setOnClickListener(onClickListener);
    }

    /**
     * 设置toolbar右侧文字显示
     *
     * @param rightText
     * @param onClickListener
     */
    public void setRightTextShow(String rightText, View.OnClickListener onClickListener) {
        rightTextView = (TextView) findViewById(R.id.tv_right);
        rightTextView.setVisibility(View.VISIBLE);
        rightTextView.setText(rightText);
        rightTextView.setOnClickListener(onClickListener);
    }

    /**
     * 设置toolbar左侧文字显示
     *
     * @param leftText
     * @param onClickListener
     */
    public void setLeftTextShow(String leftText, View.OnClickListener onClickListener) {
        rightTextView = (TextView) findViewById(R.id.tv_left);
        rightTextView.setVisibility(View.VISIBLE);
        rightTextView.setText(leftText);
        rightTextView.setOnClickListener(onClickListener);
    }

    /**
     * 设置隐藏
     */
    public void setRightTextGone() {
        if (rightTextView != null) {
            rightTextView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置是否需要点击两次退出
     *
     * @param flag
     */
    public void setDoubleClickFinish(boolean flag) {
        this.mDoubleFinish = flag;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mDoubleFinish) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                // during 2 seconds, click 'back' twice, app will exit
                if ((System.currentTimeMillis() - mLastClickBackTime) > 2000) {
                    EasyToastUtil.showToast(this, "再次点击退出");
                    mLastClickBackTime = System.currentTimeMillis();
                    return true;
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.HOME");
                    startActivity(intent);
                }
            } else {
                return true;
            }
        }
//        if (BaseClassName.equals(HomeActivity.class.getName())) {
//            Intent home = new Intent(Intent.ACTION_MAIN);
//            home.addCategory(Intent.CATEGORY_HOME);
//            startActivity(home);
//        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 自动隐藏软键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                setShowKeyboard(false);
                return false;
            } else {
                setShowKeyboard(true);
                return true;
            }
        }
        return false;
    }


    public void setShowKeyboard(boolean show) {
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        Locale newLocale = new Locale(GlobalSharedPreferences.getInstance()
                .getString("LANGUAGE", "cn"));
        Context context = MyContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(context);
    }


}
