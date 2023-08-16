package com.alibaba.excel.util;

/**
 * String utils
 *
 * @author jipengfei
 */
public class StringUtils {
    public static final String EMPTY = "";
    public static final String NULL = "null";

    private StringUtils() {}

    public static boolean isEmpty(Object str) {
        return (str == null || EMPTY.equals(str));
    }

    public static boolean isNull(Object str) {
        return (str == null || NULL.equals(str));
    }
}
