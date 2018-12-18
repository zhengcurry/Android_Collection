package com.curry.basic.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;

/**
 * @Desc : 输入框 -- 控制只能输入两位小数
 * @Author : curry
 * @Date : 2018/9/17
 */
public class EditValueFilter extends DigitsKeyListener {
    private int digits = 2;  //默认小数点后两位
    private int integerDigits = 10;  //默认最大10位

    public EditValueFilter() {
        super(false, true);
    }

    public EditValueFilter setDigits(int d) {
        digits = d;
        return this;
    }

    public EditValueFilter setIntegerDigits(int integerDigits) {
        this.integerDigits = integerDigits;
        return this;
    }

    /**
     * @param source 此时输入的文本
     * @param start  输入的起始位置
     * @param end    输入的结束位置
     * @param dest
     * @param dstart
     * @param dend
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        CharSequence out = super.filter(source, start, end, dest, dstart, dend);

        if (source.equals("-")) {
            return new SpannableStringBuilder(source, start, end);
        }

        // if changed, replace the source
        if (out != null) {
            source = out;
            start = 0;
            end = out.length();
        }

        int len = end - start;

        // if deleting, source is empty
        // and deleting can't break anything
        if (len == 0) {
            return source;
        }

        //以点开始的时候，自动在前面添加0
        if (source.toString().equals(".") && dstart == 0) {
            return "0.";
        }
        //如果起始位置为0,且第二位跟的不是".",则无法后续输入
        if (!source.toString().equals(".") && dest.toString().equals("0")) {
            return "";
        }

        int dlen = dest.length();

        //防止光标移到最前面再输入0
        if (dlen > 0 && source.toString().equals("0") && dstart == 0) {
            return "";
        }

        //确保整数位 只能输入10位
        if (!dest.toString().contains(".")) {
            if (dlen >= integerDigits && !source.toString().equals(".")) {
                return "";
            }
        } else {
            //保证末尾输入前提下 小数点前大于10位 不能再输入
            if (dest.toString().split("\\.")[0].length() >= integerDigits && dstart != dlen) {
                return "";
            }
        }
        // Find the position of the decimal .
        for (int i = 0; i < dstart; i++) {
            if (dest.charAt(i) == '.') {
                // being here means, that a number has
                // been inserted after the dot
                // check if the amount of digits is right
                return (dlen - (i + 1) + len > digits) ?
                        "" :
                        new SpannableStringBuilder(source, start, end);
            }
        }

        for (int i = start; i < end; ++i) {
            if (source.charAt(i) == '.') {
                // being here means, dot has been inserted
                // check if the amount of digits is right
                if ((dlen - dend) + (end - (i + 1)) > digits)
                    return "";
                else
                    break;  // return new SpannableStringBuilder(source, start, end);
            }
        }


        // if the dot is after the inserted part,
        // nothing can break
        return new SpannableStringBuilder(source, start, end);
    }
}
