package com.zx.codeanalysis.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhouxin
 * @since 2019/2/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Transactional {
}
