package com.curry.basic.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Desc :BigDecimal工具类
 * @Author : curry
 * @Date : 2018/9/17
 */
public class BigDecimalUtil {

    /**
     * 保留两位小数
     * 没有自动补0
     */
    public static BigDecimal saveTwoDecimal(String number) {
        if (TextUtils.isEmpty(number)) {
            return null;
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(number));
        bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal;
    }
}
