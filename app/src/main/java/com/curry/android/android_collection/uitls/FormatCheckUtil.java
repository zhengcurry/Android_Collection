package com.curry.android.android_collection.uitls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author curry
 * @date 2018/6/6
 * @Desc 格式校验
 */
public class FormatCheckUtil {

    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|//.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?//.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
//		p = Pattern.compile("^[+]{0,1}[0-9]*$");
        p = Pattern.compile("[1][345678]\\d{9}");//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public static boolean isPersonName(String str) {
        // 匹配非表情符号的正则表达式
        String reg = "^([a-z]|[A-Z]|[0-9]|[\u2E80-\u9FFF]){1,}|@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?|[wap.]{4}|[www.]{4}|[blog.]{5}|[bbs.]{4}|[.com]{4}|[.cn]{3}|[.net]{4}|[.org]{4}|[http://]{7}|[ftp://]{6}$";
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(reg);
        m = p.matcher(str);
        b = m.matches();
        return b;

    }

    /**
     * 密码验证 6-16位数字，不允许有空格
     *
     * @param str
     * @return
     */
    public static boolean isPassword(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
//        p = Pattern.compile("[0-9]{6,16}^[^ ]+$");
        //^(?![0-9]*$)[a-zA-Z0-9]{6,20}$  (6-20位字母、数字，不能是纯数字)
//        p = Pattern.compile("[0-9]{6,20}");  (6-16位数字，不允许有空格)
//        p = Pattern.compile("^[0-9A-Za-z]{6,20}$");  (6-16位字母或数字，不允许有空格)
        p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
