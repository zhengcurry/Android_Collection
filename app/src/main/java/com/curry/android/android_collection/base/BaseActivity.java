package com.curry.android.android_collection.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curry.android.android_collection.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 * @author curry
 * created at 2018/4/3 11:54
 * @desc OK
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ImmersionBar immersionBar;

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
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarView(R.id.top_view)
                .statusBarDarkFont(true,0.2f)
                .statusBarColor(R.color.white).init();
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
        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }
}

