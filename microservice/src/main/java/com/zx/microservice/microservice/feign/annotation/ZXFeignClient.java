package com.zx.microservice.microservice.feign.annotation;

import java.lang.annotation.*;

/**
 * @author zhouxin
 * @date 2018/12/17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZXFeignClient {
  String name();
}
