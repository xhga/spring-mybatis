package com.luck.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Hua wb on 2018/7/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableSplit {

    // 默认使用策略
    boolean split() default true;

    // 表名
    String value();

    // 策略
    String strategy();
}
