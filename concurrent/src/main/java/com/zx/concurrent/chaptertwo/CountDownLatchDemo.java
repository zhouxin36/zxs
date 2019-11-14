package com.zx.concurrent.chaptertwo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
/**
 * @author zhouxin
 * @date 2018/11/19
 */
public class CountDownLatchDemo {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchDemo.class);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                    logger.error(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"thread-"+i).start();
        }
        TimeUnit.MINUTES.sleep(1);
    }
}
