package com.curry.basic.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author curry
 * @date 2017/11/1
 * @Desc 根据gson格式转换
 */
public class JsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, TypeToken<T> token) {
        return gson.fromJson(json, token.getType());
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}