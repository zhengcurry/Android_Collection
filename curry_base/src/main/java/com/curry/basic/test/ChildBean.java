package com.curry.basic.test;

public class ChildBean {
    private String name;

    /****实现多对一对应表****/
    private TestBean testBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
