package com.zx.spingbootmicrowebflux.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author zhouxin
 * @date 2018-12-09
 */
public class MyApplicationEnvironmentListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
    logger.info("----->修改后的resourceLoader:{}"
        , applicationEnvironmentPreparedEvent.getSpringApplication().getResourceLoader());
  }
}
