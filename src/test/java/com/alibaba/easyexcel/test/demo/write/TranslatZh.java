package com.alibaba.easyexcel.test.demo.write;

import com.alibaba.excel.write.translation.Translator;

/**
 * @author leizhuang
 */
public class TranslatZh implements Translator {

    /**
     * 目标语言
     */
    private String language;

    public TranslatZh(String language){
        this.language = language;
    }

    @Override
    public String[] apply(String[] t) {
        for (int i = 0; i < t.length; i++) {
            t[i] = t[i] + "翻译";
        }
        return t;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
