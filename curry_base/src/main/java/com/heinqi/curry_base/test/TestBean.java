package com.heinqi.curry_base.test;

/**
 * @Desc : 用来测试
 * @Author : curry
 * @Date : 2018/12/12
 * @Update : 2018/12/12
 * @Annotation :
 */
public class TestBean {
    private String text;

    public TestBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
