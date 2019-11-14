package com.zx.codeanalysis.pattern.proxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/17
 */
public class PersonImp {

    private final static Logger logger = LoggerFactory.getLogger(PersonImp.class);

    public void findLove(){
        logger.info("findLove");
    }

    @Override
    public String toString() {
        return "toString()";
    }
}
