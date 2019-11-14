package com.zx.codeanalysis.pattern.proxy.transactional;

import java.lang.annotation.*;

/**
 * @author zhouxin
 * @since 2019/2/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HEHETransactional {
}
