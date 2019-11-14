package com.zx.spingbootmicrowebflux.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author zhouxin
 * @date 2018-12-09
 */
public class MyApplicationEnvironmentListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        logger.info("----->修改后的resourceLoader:{}"
                ,applicationEnvironmentPreparedEvent.getSpringApplication().getResourceLoader());
    }
}
