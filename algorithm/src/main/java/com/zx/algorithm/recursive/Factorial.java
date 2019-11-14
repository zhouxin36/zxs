package com.zx.algorithm.recursive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 递归 - 阶乘
 *
 * @author zhouxin
 * @since 2019/5/8
 */
public class Factorial {

    private final static Logger logger = LoggerFactory.getLogger(Factorial.class);

    private static int factorial(int num){
        if(num <= 1){
            return 1;
        }
        return num * factorial(num-1);
    }

    public static void main(String[] args) {
        logger.info("-->:{}",factorial(10));
    }
}
