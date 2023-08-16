package com.alibaba.excel.metadata;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.util.ClassUtils;
import com.alibaba.excel.write.translation.Translator;

/**
 * Basic parameter
 *
 * @author Jiaju Zhuang
 **/
public class BasicParameter {
    /**
     * You can only choose one of the {@link BasicParameter#head} and {@link BasicParameter#clazz}
     */
    private List<List<String>> head;
    /**
     * You can only choose one of the {@link BasicParameter#head} and {@link BasicParameter#clazz}
     */
    private Class clazz;

    private Map<String, Object> param;

    private Translator translator;
    /**
     * Custom type conversions override the default
     */
    private List<Converter> customConverterList;
    /**
     * Automatic trim includes sheet name and content
     */
    private Boolean autoTrim;
    /**
     * true if date uses 1904 windowing, or false if using 1900 date windowing.
     *
     * default is false
     *
     * @return
     */
    private Boolean use1904windowing;
    /**
     * A <code>Locale</code> object represents a specific geographical, political, or cultural region. This parameter is
     * used when formatting dates and numbers.
     */
    private Locale locale;

    /**
     * Whether to use scientific Format.
     *
     * default is false
     */
    private Boolean useScientificFormat;

    public List<List<String>> getHead() {
        return head;
    }

    public void setHead(List<List<String>> head) {
        this.head = head;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public boolean getIsFieldCache() {
        return ClassUtils.IS_FIELD_CACHE;
    }

    public void setIsFieldCache(boolean isFieldCache) {
        ClassUtils.IS_FIELD_CACHE = isFieldCache;
    }

    public List<Converter> getCustomConverterList() {
        return customConverterList;
    }

    public void setCustomConverterList(List<Converter> customConverterList) {
        this.customConverterList = customConverterList;
    }

    public Boolean getAutoTrim() {
        return autoTrim;
    }

    public void setAutoTrim(Boolean autoTrim) {
        this.autoTrim = autoTrim;
    }

    public Boolean getUse1904windowing() {
        return use1904windowing;
    }

    public void setUse1904windowing(Boolean use1904windowing) {
        this.use1904windowing = use1904windowing;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Boolean getUseScientificFormat() {
        return useScientificFormat;
    }

    public void setUseScientificFormat(Boolean useScientificFormat) {
        this.useScientificFormat = useScientificFormat;
    }
}
