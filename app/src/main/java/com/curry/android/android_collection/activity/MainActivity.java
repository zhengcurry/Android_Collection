package com.curry.android.android_collection.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.activity.chart.BarChartActivity;
import com.curry.android.android_collection.activity.chart.CombinedChartActivity;
import com.curry.android.android_collection.activity.chart.LineChartActivity;
import com.curry.android.android_collection.base.BaseActivity;
import com.example.makejarlibrary.MakeJar;

import org.w3c.dom.Text;

import java.util.Locale;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView test = findViewById(R.id.text);
        test.setText(MakeJar.getStr());
    }

    @OnClick({R.id.btn_test_immersion, R.id.btn_line_chart, R.id.btn_bar_chart,
            R.id.btn_combined_chart, R.id.btn_view, R.id.btn_change})
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
            case R.id.btn_change:             //自定义View
//                changeLanguage();
                saveLanguage();
                recreate();
                break;
        }
    }

    private void changeLanguage() {

        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        config.locale = Locale.ENGLISH;
        resources.updateConfiguration(config, dm);
    }

    private void saveLanguage() {
        SharedPreferences sharedPreferences =
                getSharedPreferences("curry", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getString("language", "cn").equals("cn")) {
            editor.putString("language", "en");
        } else {
            editor.putString("language", "cn");
        }
        editor.commit();
    }
}


