package com.curry.android.android_collection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.curry.android.android_collection.R;
import com.curry.android.android_collection.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @OnClick(R.id.btn_test_immersion)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_immersion:
                startActivity(new Intent(this, TestImmersionActivity.class));
                break;
        }
    }

}
