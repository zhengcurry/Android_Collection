package com.heinqi.curry_base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.heinqi.curry_base.base.BaseApplication;

import java.util.Set;

import static com.heinqi.curry_base.constant.Constant.BASE_NAME;

/**
 * @author curry
 * @date 2018/5/23
 * @Desc SP管理类
 * <p>
 * 注意：保存sp 先执行editOpen，再执行editClose
 */

public class GlobalSharedPreferences {
    private static GlobalSharedPreferences globalSharedPreferences;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;

    public static GlobalSharedPreferences getInstance() {
        if (globalSharedPreferences == null) {
            globalSharedPreferences = new GlobalSharedPreferences(BaseApplication.getApplication());
        }
        return globalSharedPreferences;
    }

    public GlobalSharedPreferences(Context context) {
        mPrefs = context.getSharedPreferences(BASE_NAME, Context.MODE_PRIVATE);
    }

    public GlobalSharedPreferences editOpen() {
        editor = mPrefs.edit();
        return this;
    }

    public void editClose() {
        editor.commit();
    }

    public GlobalSharedPreferences putString(String name, String text) {
        editor.putString(name, text);
        return this;
    }

    public String getString(String name, String defValue) {
        return mPrefs.getString(name, defValue);
    }

    public GlobalSharedPreferences putInt(String name, int value) {
        editor.putInt(name, value);
        return this;
    }

    public int getInt(String name, int defValue) {
        return mPrefs.getInt(name, defValue);
    }

    public GlobalSharedPreferences putLong(String name, long value) {
        editor.putLong(name, value);
        return this;
    }

    public long getLong(String name, long defValue) {
        return mPrefs.getLong(name, defValue);
    }

    public GlobalSharedPreferences putBoolean(String name, boolean value) {
        editor.putBoolean(name, value);
        return this;
    }

    public boolean getBoolean(String name, boolean defValue) {
        return mPrefs.getBoolean(name, defValue);
    }

    public GlobalSharedPreferences putStringSet(String name, Set<String> value) {
        editor.putStringSet(name, value);
        return this;
    }

    public Set<String> getStringSet(String name, Set<String> defValue) {
        return mPrefs.getStringSet(name, defValue);
    }

    public GlobalSharedPreferences remove(String name) {
        editor.remove(name);
        return this;
    }

    public GlobalSharedPreferences clearAll() {
        editOpen();
        editor.clear();
        editClose();
        return this;
    }
}
