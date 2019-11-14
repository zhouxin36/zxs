package com.zx.codeanalysis.spring.springv2.bean;

import java.util.StringJoiner;

/**
 * @author zhouxin
 * @date 2018/11/13
 */
public class ZXBeanDefinition {

    private String className;

    private String factoryName;

    private Boolean isLazy = false;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Boolean getLazy() {
        return isLazy;
    }

    public void setLazy(Boolean lazy) {
        isLazy = lazy;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ZXBeanDefinition.class.getSimpleName() + "[", "]")
                .add("className='" + className + "'")
                .add("factoryName='" + factoryName + "'")
                .add("isLazy=" + isLazy)
                .toString();
    }
}
