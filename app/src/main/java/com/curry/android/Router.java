package com.curry.android;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 自定义注释
 *
 * author curry
 * create 2019/3/6
 */
@Target(ElementType.TYPE)
public @interface Router {
    String Router();
}
