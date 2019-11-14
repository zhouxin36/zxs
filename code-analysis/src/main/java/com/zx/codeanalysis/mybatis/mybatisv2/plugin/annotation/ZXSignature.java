package com.zx.codeanalysis.mybatis.mybatisv2.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ZXSignature {
    Class<?> clazz();
    String method();
    Class<?>[] args();
}
