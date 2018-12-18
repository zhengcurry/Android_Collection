package com.curry.basic.test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.heinqi.curry_base.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        initView();

        /**
         * 生成数据库
         */
        SQLiteDatabase db = Connector.getDatabase();
        testLitePal();
    }

    /**
     * 数据库测试
     */
    private void testLitePal() {
        TestBean testBean = new TestBean("test1");
        //返回boolean值
        boolean flag = testBean.save();
        //抛出异常
        testBean.saveThrows();

        /**直接保存多个实体类**/
        List<TestBean> testBeanList = new ArrayList<>();
        testBeanList.add(new TestBean("test2"));
        testBeanList.add(new TestBean("test3"));
        testBeanList.add(new TestBean("test4"));
        LitePal.saveAll(testBeanList);

        /**改**/
        ContentValues values = new ContentValues();
        values.put("text", "updateText2");
        LitePal.update(TestBean.class, values, 1);
        //将所有text为text3的值都改成updateText3
        ContentValues values2 = new ContentValues();
        values.put("text", "updateText3");
        LitePal.updateAll(TestBean.class, values2, "text = ?", "test3");

        TestBean update = new TestBean("update");
        update.update(2);
        TestBean update3 = new TestBean("update4");
        if (update3.isSaved())
            update3.updateAll("text = ?", "test4");

        /**修改某一字段为默认值**/
//        TestBean updateNews = new TestBean();
//        updateNews.setToDefault("text");//字段名称
//        updateNews.updateAll();

        /**删**/
        LitePal.delete(TestBean.class, 2);
        TestBean testBeanDelete = new TestBean();
        testBeanDelete.setText("delete");
        testBeanDelete.save();
        testBeanDelete.delete();

        /**查**/
        LitePal.find(TestBean.class, 2);
        //激进查询 把与关联的全部查询出来
        //不推荐；可能造成查询速度变慢、没法继续查询关联表的数据
        //代替写法：在实体类中写
        // public List<ChildBean> getChildBeans() {
        //   return LitePal.where("id = ?", String.valueOf(id)).find(ChildBean.class);
        // }
        LitePal.find(TestBean.class, 2, true);
        LitePal.findAll(TestBean.class);
        //offset偏移量获取11-20条
//        LitePal.select(/*列名*/).where().order("test desc").limit(10).offset(10).find();

        /**聚合函数**/
       /* LitePal.count(TestBean.class);//统计行数
        LitePal.sum(TestBean.class, "test", int.class);//求和
        LitePal.average(TestBean.class, "test");//求平均值
        LitePal.max(TestBean.class, "test", int.class);//求最大
        LitePal.min(TestBean.class, "test", int.class);//求最小*/
    }

    private void initView() {
        mText = findViewById(R.id.text);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(TestBean testBean) {
        mText.setText(testBean.getText());
    }

    /**
     * Learn.md-2
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button) {
            Intent intent = new Intent(Test1Activity.this, Test2Activity.class);
            startActivity(intent);
        } else {
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        /**注册eventbus**/
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        /**解除注册eventbus**/
        if (isFinishing()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
