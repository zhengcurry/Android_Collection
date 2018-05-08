package com.curry.android.android_collection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.activity.chart.BarChartActivity;
import com.curry.android.android_collection.activity.chart.CombinedChartActivity;
import com.curry.android.android_collection.activity.chart.LineChartActivity;
import com.curry.android.android_collection.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn_test_immersion, R.id.btn_line_chart, R.id.btn_bar_chart,
            R.id.btn_combined_chart, R.id.btn_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_immersion:   //沉浸式状态栏
                startActivity(new Intent(this, TestImmersionActivity.class));
                break;
            case R.id.btn_line_chart:       //折线图
                startActivity(new Intent(this, LineChartActivity.class));
                break;
            case R.id.btn_bar_chart:        //柱状图
                startActivity(new Intent(this, BarChartActivity.class));
                break;
            case R.id.btn_combined_chart:   //复合图
                startActivity(new Intent(this, CombinedChartActivity.class));
                break;
            case R.id.btn_view:             //自定义View
                startActivity(new Intent(this, CanvasActivity.class));
                break;
        }
    }

}
