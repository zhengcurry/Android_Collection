package com.curry.android.android_collection.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.bean.TestBean;

import org.greenrobot.eventbus.EventBus;

public class Test2Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * click
     */
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
    }

    private void initView() {
        mButton2 = findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button2) {
            EventBus.getDefault().post(new TestBean("发送发送发送发送发送发送"));
            finish();
        } else {
        }
    }
}
