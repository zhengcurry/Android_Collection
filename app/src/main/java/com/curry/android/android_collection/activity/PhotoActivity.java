package com.curry.android.android_collection.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.curry.android.android_collection.R;

/**
 * @Desc : 选择图片
 * @Author : curry
 * @Date : 2018/10/29
 */
public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

    public void onlyChoicePicture(View view) {
        Intent intent = new Intent();

    }
}
