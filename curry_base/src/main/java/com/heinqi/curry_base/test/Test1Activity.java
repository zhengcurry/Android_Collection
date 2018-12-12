package com.heinqi.curry_base.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.heinqi.curry_base.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        initView();
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
