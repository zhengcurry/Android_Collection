package com.curry.android.android_collection.uitls;

import android.content.Context;
import android.content.SharedPreferences;

import static com.curry.android.android_collection.constant.Constant.BASE_NAME;

/**
 * @author curry
 * @date 2018/5/23
 * @Desc 添加时先open 然后commit
 */

public class GlobalSharedPreferences {
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;

    public GlobalSharedPreferences(Context context) {
        mPrefs = context.getSharedPreferences(BASE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 执行，添加字段前 需要执行
     */
    public GlobalSharedPreferences editOpen() {
        editor = mPrefs.edit();
        return this;
    }

    /**
     * 执行，添加字段后 需要执行
     */
    public GlobalSharedPreferences editCommit() {
        editor.commit();
        return this;
    }

    public void remove(String name) {
        editor.remove(name);
    }

    /**
     * 清除所有
     *
     * @return
     */
    public GlobalSharedPreferences clearAll() {
        editor.clear();
        return this;
    }

    public GlobalSharedPreferences putString(String name, String text) {
        editor.putString(name, text);
        return this;
    }

    public GlobalSharedPreferences putInt(String name, int value) {
        editor.putInt(name, value);
        return this;
    }

    public GlobalSharedPreferences putBoolean(String name, boolean value) {
        editor.putBoolean(name, value);
        return this;
    }


    public GlobalSharedPreferences putLong(String name, long value) {
        editor.putLong(name, value);
        return this;
    }

    public String getString(String name, String defValue) {
        return mPrefs.getString(name, defValue);
    }

    public int getInt(String name, int defValue) {
        return mPrefs.getInt(name, defValue);
    }

    public long getLong(String name, long defValue) {
        return mPrefs.getLong(name, defValue);
    }

    public boolean getBoolean(String name, boolean defValue) {
        return mPrefs.getBoolean(name, defValue);
    }
}
