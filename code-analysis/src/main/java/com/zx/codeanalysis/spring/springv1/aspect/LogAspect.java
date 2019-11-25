package com.zx.codeanalysis.spring.springv1.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/30
 */
public class LogAspect {

  private static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

  public void before() {
    LOGGER.info("------------->before");
  }

  public void after() {
    LOGGER.info("------------->after");
  }
}
