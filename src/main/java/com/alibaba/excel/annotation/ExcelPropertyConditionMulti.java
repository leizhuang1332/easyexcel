package com.alibaba.excel.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ExcelPropertyConditionMulti {

    String[] value() default {""};

    String test() default "";
}
