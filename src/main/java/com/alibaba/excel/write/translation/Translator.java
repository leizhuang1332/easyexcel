package com.alibaba.excel.write.translation;

/**
 * 翻译表头
 *
 * @author leizhuang
 */
public interface Translator {
    /**
     * 翻译
     * @param t
     * @return
     */
    String[] apply(String[] t);
}
