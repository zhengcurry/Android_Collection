package com.curry.android.android_collection.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.activity.chart.BarChartActivity;
import com.curry.android.android_collection.activity.chart.CombinedChartActivity;
import com.curry.android.android_collection.activity.chart.LineChartActivity;
import com.curry.android.android_collection.base.BaseActivity;
import com.curry.basic.test.TestBean;
import com.curry.basic.widget.BackgroundDarkPopupWindow;
import com.example.makejarlibrary.MakeJar;
import com.heinqi.curry_kotlin.KtMainActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
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

        testDB();

        TextView test = findViewById(R.id.text);
        TextView tvShowDb = findViewById(R.id.tv_show_db);
        test.setText(MakeJar.getStr());
        tvShowDb.setText(LitePal.find(TestBean.class, 1).getText());


        test();
    }

    private void testDB() {
        Connector.getDatabase();

        TestBean testBean = new TestBean("testDB1");
        //返回boolean值
        boolean flag = testBean.save();
        //抛出异常
        testBean.saveThrows();

        /**直接保存多个实体类**/
        List<TestBean> testBeanList = new ArrayList<>();
        testBeanList.add(new TestBean("testDB2"));
        testBeanList.add(new TestBean("testDB3"));
        testBeanList.add(new TestBean("testDB4"));
        LitePal.saveAll(testBeanList);
    }

    @OnClick({R.id.btn_test_immersion, R.id.btn_line_chart, R.id.btn_bar_chart,
            R.id.btn_combined_chart, R.id.btn_view, R.id.btn_change, R.id.btn_skip_to_kotlin})
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
            case R.id.btn_skip_to_kotlin:   //自定义View
                startActivity(new Intent(this, KtMainActivity.class));
                break;
            case R.id.btn_change:           //自定义View
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


    private void test() {
        getWindow().getDecorView();
        TextView textView = findViewById(R.id.test);
        textView.scrollBy(100, 100);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

//        textView.onTouchEvent(MotionEvent.ACTION_DOWN);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


}


