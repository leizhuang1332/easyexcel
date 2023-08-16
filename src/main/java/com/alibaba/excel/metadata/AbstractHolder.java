package com.alibaba.excel.metadata;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.util.ClassUtils;
import com.alibaba.excel.write.translation.Translator;

/**
 * Write/read holder
 *
 * @author Jiaju Zhuang
 */
public abstract class AbstractHolder implements ConfigurationHolder {
    /**
     * Record whether it's new or from cache
     */
    private Boolean newInitialization;
    /**
     * You can only choose one of the {@link AbstractHolder#head} and {@link AbstractHolder#clazz}
     */
    private List<List<String>> head;
    /**
     * You can only choose one of the {@link AbstractHolder#head} and {@link AbstractHolder#clazz}
     */
    private Class clazz;

    private Map<String, Object> param;

    private Translator translator;
    /**
     * Some global variables
     */
    private GlobalConfiguration globalConfiguration;
    /**
     * <p>
     * Read key:
     * <p>
     * Write key:
     */
    private Map<String, Converter> converterMap;

    public AbstractHolder(BasicParameter basicParameter, AbstractHolder prentAbstractHolder) {
        this.newInitialization = Boolean.TRUE;
        if (basicParameter.getHead() == null && basicParameter.getClazz() == null && prentAbstractHolder != null) {
            this.head = prentAbstractHolder.getHead();
        } else {
            this.head = basicParameter.getHead();
        }
        if (basicParameter.getHead() == null && basicParameter.getClazz() == null && prentAbstractHolder != null) {
            this.clazz = prentAbstractHolder.getClazz();
        } else {
            this.clazz = basicParameter.getClazz();
        }
        if (basicParameter.getParam() == null && prentAbstractHolder != null) {
            this.param = prentAbstractHolder.getParam();
        } else {
            this.param = basicParameter.getParam();
        }
        if (basicParameter.getTranslator() == null && prentAbstractHolder != null) {
            this.translator = prentAbstractHolder.getTranslator();
        } else {
            this.translator = basicParameter.getTranslator();
        }
        ClassUtils.IS_FIELD_CACHE = basicParameter.getIsFieldCache();
        this.globalConfiguration = new GlobalConfiguration();
        if (basicParameter.getAutoTrim() == null) {
            if (prentAbstractHolder == null) {
                globalConfiguration.setAutoTrim(Boolean.TRUE);
            } else {
                globalConfiguration.setAutoTrim(prentAbstractHolder.getGlobalConfiguration().getAutoTrim());
            }
        } else {
            globalConfiguration.setAutoTrim(basicParameter.getAutoTrim());
        }

        if (basicParameter.getLocale() == null) {
            if (prentAbstractHolder == null) {
                globalConfiguration.setLocale(Locale.getDefault());
            } else {
                globalConfiguration.setLocale(prentAbstractHolder.getGlobalConfiguration().getLocale());
            }
        } else {
            globalConfiguration.setLocale(basicParameter.getLocale());
        }

    }

    public Boolean getNewInitialization() {
        return newInitialization;
    }

    public void setNewInitialization(Boolean newInitialization) {
        this.newInitialization = newInitialization;
    }

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

    public GlobalConfiguration getGlobalConfiguration() {
        return globalConfiguration;
    }

    public void setGlobalConfiguration(GlobalConfiguration globalConfiguration) {
        this.globalConfiguration = globalConfiguration;
    }

    public Map<String, Converter> getConverterMap() {
        return converterMap;
    }

    public void setConverterMap(Map<String, Converter> converterMap) {
        this.converterMap = converterMap;
    }

    @Override
    public Map<String, Converter> converterMap() {
        return getConverterMap();
    }

    @Override
    public GlobalConfiguration globalConfiguration() {
        return getGlobalConfiguration();
    }

    @Override
    public boolean isNew() {
        return getNewInitialization();
    }

}
