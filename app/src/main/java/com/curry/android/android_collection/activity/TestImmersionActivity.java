package com.curry.android.android_collection.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.uitls.ImmersionBarUtil;
import com.curry.android.android_collection.uitls.SoftKeyInputUtil;

import butterknife.ButterKnife;

/**
 * @author curry
 * created at 2018/4/9 11:02
 * @desc 测试沉浸式状态栏
 */
public class TestImmersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_immersion);
        ButterKnife.bind(this);
        SoftKeyInputUtil.assistActivity(this);
        ImmersionBarUtil
                .newInstance(this)
//                .setColor(Color.parseColor("#FF4081"))
                .setDrawable(getResources().getDrawable(R.drawable.gradient))
                .init();
    }
}
