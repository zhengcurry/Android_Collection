package com.heinqi.curry_base.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.util.List;

/**
 * 字符串处理工具类
 *
 * @author Duke-Alliance Team
 * @version 1.0
 * @history 2013-7-23 create
 * @since JDK1.6
 */
public class StringUtil {

    /**
     * 替换成--
     *
     * @param value
     * @return
     */
    public static String replaceNull(String value) {
        return replaceNull(value, "--");
    }

    public static String replaceNull(String value, String replaceStr) {
        return TextUtils.isEmpty(value) ? replaceStr : value;
    }

    /**
     * 判断为空
     *
     * @param value
     * @return
     */
    public static String judgeNull(String value) {
        return TextUtils.isEmpty(value) ? "" : value;
    }

    /**
     * list中获取全部字符串，以逗号分隔
     *
     * @param list
     * @return
     */
    public static String getAllString(List<String> list) {
        return getAllStringWithSeparator(list, ",", "");
    }

    public static String getAllStringWithSeparator(List<String> list, String separator, String defaultText) {
        if (list != null && list.size() > 0) {
            String allText = "";
            for (String text : list) {
                allText = allText + " " + text.trim();
            }
            return allText.trim().replaceAll(" ", separator);
        } else {
            return defaultText;
        }
    }


    /**
     * 获取字符长度 ,一个汉字当做2个英文字符
     *
     * @param str
     * @return
     */
    public static int getLength(String str) {
        int result = 0;
        try {
            if (hasValue(str)) {
                char[] cs = str.toCharArray();
                for (char c : cs) {
                    result = result + ((c >= 0x4e00 && c <= 0x9fa5) ? 2 : 1);
                }
            }
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * 获取字符长度 ,一个汉字当做2个英文字符
     *
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean checkRange(String str, int min, int max) {
        int result = getLength(str);
        if (result < min || result > max) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否有中文
     *
     * @param str
     * @return
     */
    public static boolean hasChinese(String str) {
        if (str == null) {
            return false;
        }
        if (str.getBytes().length != str.length()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否为 null 或 空字符串
     *
     * @return if "str" is null or empty then return true else return false
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (null == obj || obj.toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符是否为 null 或 空字符串
     *
     * @return if "str" is null or empty then return false else return true
     */
    public static boolean hasValue(Object obj) {
        if (obj != null && obj.toString().trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符是否为 null 或 空字符串
     *
     * @param str
     * @return str
     */
    public static String NullOrEmpty(String str) {
        try {
            if (str == null) {
                return "";
            } else {
                str = str.trim();
                str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            }
        } catch (Exception e) {

        }
        return str;
    }

    /**
     * 冒泡排序
     *
     * @param x
     * @return
     */
    public static int[] bubbleSort(int[] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                if (x[i] > x[j]) {
                    int temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;
                }
            }
        }
        return x;
    }

    /**
     * 将字符串转换成Bitmap类型
     *
     * @param string base64string
     * @return
     */
    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = android.util.Base64.decode(string, android.util.Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
