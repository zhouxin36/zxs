package com.zx.jdkanalysis.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2019/1/11
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalDemo.class);

    public static void main(String[] args) {
        new Thread(()->{
            threadLocal.set("threadLocal");
            inheritableThreadLocal.set("inheritableThreadLocal");
            new Thread(()->{
                logger.info("----------->threadLocal:{}",threadLocal.get());
                logger.info("----------->inheritableThreadLocal:{}",inheritableThreadLocal.get());
            },"thread-in").start();
            logger.info("----------->threadLocal:{}",threadLocal.get());
            logger.info("----------->inheritableThreadLocal:{}",inheritableThreadLocal.get());
        },"thread-out").start();
        logger.info("----------->threadLocal:{}",threadLocal.get());
        logger.info("----------->inheritableThreadLocal:{}",inheritableThreadLocal.get());
    }
}
