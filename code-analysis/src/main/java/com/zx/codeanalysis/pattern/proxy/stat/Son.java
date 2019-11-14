package com.zx.codeanalysis.pattern.proxy.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/17
 */
public class Son implements Person {

    private final static Logger logger = LoggerFactory.getLogger(Son.class);

    public void findLove(){
        logger.info("呵呵");
    }

    @Override
    public void buy() {
        logger.info("买买买");
    }
}
