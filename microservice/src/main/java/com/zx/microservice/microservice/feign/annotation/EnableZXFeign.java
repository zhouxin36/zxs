package com.zx.microservice.microservice.feign.annotation;

import com.zx.microservice.microservice.feign.config.ZXFeignClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhouxin
 * @date 2018/12/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ZXFeignClientsRegistrar.class)
public @interface EnableZXFeign {

    Class<?>[] classes();
}
