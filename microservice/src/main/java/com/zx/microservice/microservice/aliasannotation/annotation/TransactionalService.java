package com.zx.microservice.microservice.aliasannotation.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * @author zhouxin
 * @date 2018/12/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Service
@Transactional
public @interface TransactionalService {

  /**
   * 定义别名，派生属性
   *
   * @return
   */
  @AliasFor(annotation = Service.class, attribute = "value")
  String value() default "";
}
