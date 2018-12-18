package com.curry.basic.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.curry.basic.utils.StringUtil.hasValue;

/**
 * @author curry
 * @date 2017/10/9
 * @Desc 时间与字符串互转工具
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
    /**
     * 判断是否是正确的时间格式
     *
     * @param datevalue
     * @param dateFormat
     * @return
     */
    public static boolean isDateString(String datevalue, String dateFormat) {
        if (!hasValue(datevalue)) {
            return false;
        }
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
            java.util.Date dd = fmt.parse(datevalue);
            if (datevalue.equals(fmt.format(dd))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 时间转换
     *
     * @param date
     * @return
     */
    public static String getTime(Date date, String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(date);
    }

    public static long getTimeLong(Date date, String strFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(strFormat);
            return format.parse(format.format(date)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getThisYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }

    /**
     * 月份是0-11
     *
     * @return
     */
    public static int getThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.MONTH);
    }

    public static int getThisDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.DATE);
    }

    public static int getThisHour() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getNextDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取下一个小时
     *
     * @return
     */
    public static int getNextHour() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getThisMinute() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.MINUTE);
    }

    public static int getThisSecond() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.SECOND);
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

    /**
     * 字符串 转成 Date类型
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * strTime的时间格式必须要与formatType的时间格式相同
     *
     * @param strTime
     * @param formatType
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
        if (TextUtils.isEmpty(strTime)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = simpleDateFormat.parse(strTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType) {
        if (currentTime != 0) {
            Date date = longToDate(currentTime, formatType); // long类型转成Date类型
            String strTime = dateToString(date, formatType); // date类型转成String
            return strTime;
        } else {
            return "--";
        }
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType) {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * Date型 转成 String型
     *
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * 时间换算
     *
     * @param time
     * @return
     */
    public static String calTime(long time) {
        String result = "";
        // 计算天
        int days = (int) (time / 1000 / 3600 / 24);
        if (days > 0) {
            result += days + "天";
        }
        // 计算小时
        time -= days * 24 * 3600 * 1000;
        int hours = (int) (time / 1000 / 3600);
        if (hours > 0) {
            result += hours + "小时";
        }
        // 计算分钟
        time -= hours * 3600 * 1000;
        int minutes = (int) (time / 1000 / 60);
        if (minutes > 0) {
            result += minutes + "分钟";
        }
        return result;
    }

    public Date convertStringToDate(String time, SimpleDateFormat formatter) {
        Date date = new Date();
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 地图：路线规划中使用
     *
     * @param second
     * @return
     */
    public static String getFriendlyTime(int second) {
        if (second > 3600) {
            int hour = second / 3600;
            int miniate = (second % 3600) / 60;
            return hour + "小时" + miniate + "分钟";
        }
        if (second >= 60) {
            int miniate = second / 60;
            return miniate + "分钟";
        }
        return second + "秒";
    }


    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static Calendar getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(beginDate);
        startCalendar.set(Calendar.DATE, startCalendar.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(startCalendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        return endCalendar;
    }
}