package com.zx.microservice.microservice.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhouxin
 * @date 2018-12-02
 */
public class FutureDemo {

    private static final Logger logger = LoggerFactory.getLogger(FutureDemo.class);
    public static void main(String[] args) {

        ExecutorService executorService =
                Executors.newFixedThreadPool(1);
        Random random = new Random();
        Future<String> submit = executorService.submit(() -> {
            int value = random.nextInt(200);
            logger.error("睡觉：——————" + value);
            Thread.sleep(value);
            return "hello";
        });
        try {
            logger.error(submit.get(170, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            logger.error("抛异常了");
        }
        executorService.shutdown();
    }
}
