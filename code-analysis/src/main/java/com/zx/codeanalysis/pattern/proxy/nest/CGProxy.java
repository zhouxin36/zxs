package com.zx.codeanalysis.pattern.proxy.nest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/3/20
 */
public class CGProxy {

    private final Logger logger = LoggerFactory.getLogger(CGProxy.class);

    public void say() {
        logger.info("say");
    }
}