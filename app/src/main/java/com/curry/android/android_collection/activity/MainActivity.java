package com.curry.android.android_collection.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.activity.chart.BarChartActivity;
import com.curry.android.android_collection.activity.chart.CombinedChartActivity;
import com.curry.android.android_collection.activity.chart.LineChartActivity;
import com.curry.android.android_collection.base.BaseActivity;
import com.curry.android.android_collection.bean.TestBean;
import com.curry.android.android_collection.test.TestActivity;
import com.example.makejarlibrary.MakeJar;
import com.example.testjarlibrary.TestAarActivity;
import com.example.testjarlibrary.TestJar;
import com.heinqi.curry_kotlin.KtMainActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        test.setText(MakeJar.getStr() + "----" + TestJar.getStr());
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestAarActivity.class));
            }
        });
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

    //    @OnClick({R.id.btn_test_immersion, R.id.btn_line_chart, R.id.btn_bar_chart,
//            R.id.btn_combined_chart, R.id.btn_view, R.id.btn_change, R.id.btn_skip_to_kotlin})
    public void onClickEvent(View view) {
        int i = view.getId();//沉浸式状态栏
        if (i == R.id.btn_test_immersion) {
            startActivity(new Intent(this, TestImmersionActivity.class));

        } else if (i == R.id.btn_line_chart) {
            startActivity(new Intent(this, LineChartActivity.class));

        } else if (i == R.id.btn_bar_chart) {
            startActivity(new Intent(this, BarChartActivity.class));

        } else if (i == R.id.btn_combined_chart) {
            startActivity(new Intent(this, CombinedChartActivity.class));

        } else if (i == R.id.btn_view) {
            startActivity(new Intent(this, CanvasActivity.class));

        } else if (i == R.id.btn_skip_to_kotlin) {
            startActivity(new Intent(this, KtMainActivity.class));

        } else if (i == R.id.btn_change) {//                changeLanguage();
            saveLanguage();
            recreate();

        } else if (i == R.id.test) {
            startActivity(new Intent(this, TestActivity.class));

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


