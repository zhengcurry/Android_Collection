package com.curry.android.android_collection.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.curry.android.android_collection.R;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * @author curry
 *         created at 2018/4/3 11:54
 * @desc OK
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ImmersionBar immersionBar;
    private ActivityManager activityManager;

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_layout);
        setContentView();
        /*******注册要在setContentView之后*****/
        ButterKnife.bind(this);

        //添加到Activity管理
        activityManager = ActivityManager.getAppManager();
        activityManager.addActivity(this);

        immersionBar = ImmersionBar.with(this);
        immersionBar
                .statusBarView(R.id.top_view)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.white)
                .keyboardEnable(true)
                .init();
    }

    /**
     * 将布局添加到base_layout
     */
    public void setContentView() {
        LinearLayout baseLayout = findViewById(R.id.base_layout);
        if (baseLayout == null) return;
        baseLayout.addView(View.inflate(this, getLayoutId(), null),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.removeActivity(BaseActivity.class);
        if (immersionBar != null) {
            immersionBar.destroy();
        }
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
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}

