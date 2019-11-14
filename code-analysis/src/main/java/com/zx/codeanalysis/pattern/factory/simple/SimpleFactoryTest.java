package com.zx.codeanalysis.pattern.factory.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class SimpleFactoryTest {

    private final static Logger logger = LoggerFactory.getLogger(SimpleFactoryTest.class);

    public static void main(String[] args) {
        logger.info(new SimpleFactory().getMilk("蒙牛").toString());
    }
}
