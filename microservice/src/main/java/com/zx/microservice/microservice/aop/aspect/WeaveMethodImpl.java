package com.zx.microservice.microservice.aop.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/3/8
 */
public class WeaveMethodImpl implements WeaveMethod {

    private static final Logger logger = LoggerFactory.getLogger(WeaveMethodImpl.class);
    @Override
    public String say() {
        logger.info("WeaveMethodImpl#say");
        return "say";
    }

    @Override
    public String eat() {
        logger.info("WeaveMethodImpl#eat");
        return "eat";
    }
}
