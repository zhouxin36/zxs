package com.zx.codeanalysis.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhouxin
 * @date 2018/10/25
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
  String value() default "";
}
