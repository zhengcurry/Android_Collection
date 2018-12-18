package com.curry.basic.test;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc : 用来测试
 * @Author : curry
 * @Date : 2018/12/12
 * @Update : 2018/12/12
 * @Annotation :
 */
public class TestBean extends LitePalSupport {
    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /****实现一对一对应表****/
    private OnlyBean onlyBean;

    /****实现多对一对应表****/
    private List<ChildBean> childBeans = new ArrayList<>();

    /****实现多对多对应表****/
    private List<AllBean> allBeans = new ArrayList<>();

    public TestBean() {
    }

    public TestBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ChildBean> getChildBeans() {
        return LitePal.where("name = ?", String.valueOf(id)).find(ChildBean.class);
    }

    public void setChildBeans(List<ChildBean> childBeans) {
        this.childBeans = childBeans;
    }
}
