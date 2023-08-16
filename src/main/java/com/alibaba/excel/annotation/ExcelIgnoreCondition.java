package com.alibaba.excel.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ExcelIgnoreCondition {
    String test() default "";
}
