package com.curry.basic.test;

import java.util.ArrayList;
import java.util.List;

public class AllBean {
    private String name;

    public AllBean(String name) {
        this.name = name;
    }

    /****实现多对多对应表****/
    private List<TestBean> testBeans = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
