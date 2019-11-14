package com.zx.codeanalysis.pattern.factory.abstr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class AbstractTest {

    private final static Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    public static void main(String[] args) {
        logger.info(new MilkFactory().getMengniu().toString());
    }
}
