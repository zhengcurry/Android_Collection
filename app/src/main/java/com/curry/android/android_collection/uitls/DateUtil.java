package com.curry.android.android_collection.uitls;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author curry
 * @date 2017/10/9
 * @Desc 时间与字符串互转工具
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {

    /**
     * Date型 转成 String型
     *
     * @param date
     * @param formatType
     * @return
     */
    public static String dateToString(Date date, String formatType) {
        if (date != null) {
            return new SimpleDateFormat(formatType).format(date);
        } else {
            return "----";
        }
    }

    /**
     * String型 转成 Date类型
     *
     * @param strTime    string类型的时间 strTime的时间格式必须要与formatType的时间格式相同
     * @param formatType 要转换的格式
     * @return
     */
    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = simpleDateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串时间转成long型
     *
     * @param strTime
     * @param formatType
     * @return
     */
    public static Long stringToLong(String strTime, String formatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = simpleDateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * long型 转成 string类型
     *
     * @param currentTime long类型的时间
     * @param formatType  string类型的时间格式
     * @return
     */
    public static String longToString(long currentTime, String formatType) {
        if (currentTime != 0) {
            Date date = longToDate(currentTime, formatType); // long类型转成Date类型
            String strTime = dateToString(date, formatType); // date类型转成String
            return strTime;
        } else {
            return "--";
        }
    }

    /**
     * long型 转成 date类型
     *
     * @param currentTime long类型的时间
     * @param formatType  要转换的时间格式
     * @return
     */
    public static Date longToDate(long currentTime, String formatType) {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * 获取当前日期的 年 月 日 时 分 秒
     *
     * @param date         日期 : 当前日期new Date()
     * @param calendarFlag Calendar.YEAR\.MONTH\.DATE\.HOUR_OF_DAY\.MINUTE\.SECOND\
     * @return
     */
    public static int getRandomTime(Date date, int calendarFlag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (calendarFlag == Calendar.MONTH || calendarFlag == Calendar.DATE) {
            return cal.get(calendarFlag) + 1;
        } else {
            return cal.get(calendarFlag);
        }
    }

    /**
     * 判断两个时间段时候存在交集
     *
     * @param beginTime1
     * @param endTime1
     * @param beginTime2
     * @param endTime2
     * @return true->有交集
     */
    public static boolean comparisonRQ(String beginTime1, String endTime1, String beginTime2, String endTime2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date b1 = df.parse(beginTime1);
            Date e1 = df.parse(endTime1);
            Date b2 = df.parse(beginTime2);
            Date e2 = df.parse(endTime2);
            if (b1.getTime() < b2.getTime() && b2.getTime() < e1.getTime()) {
                return true;
            }
            if (b1.getTime() < e2.getTime() && e2.getTime() < e1.getTime()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}