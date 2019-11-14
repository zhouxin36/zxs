package com.zx.codeanalysis.pattern.proxy.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/17
 */
public class Father {

    private final static Logger logger = LoggerFactory.getLogger(Father.class);

    private Person person;

    public  Father(Person person){
        this.person = person;
    }
    public void findLove(){
        logger.info("呵呵前");
        person.findLove();
        logger.info("呵呵后");
    }
}
