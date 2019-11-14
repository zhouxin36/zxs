package com.zx.concurrent.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @since 2019/3/20
 */
public class ThreadPoolTest {

    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        threadLocal.set("threadLocal");
        inheritableThreadLocal.set("inheritableThreadLocal");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 15; i++) {
            TimeUnit.SECONDS.sleep(1);
            executorService.execute(()->{
                System.out.println("-------------------------------------华丽的分割线---------------------------------------");
                logger.info("->t:{}*****it:{}"
                        ,threadLocal.get(),inheritableThreadLocal.get());
                final int t = atomicInteger.incrementAndGet();
                final int it = atomicInteger.incrementAndGet();
                threadLocal.set(t+"");
                inheritableThreadLocal.set(it+"");
                String threadName = Thread.currentThread().getName();
                logger.info("^>t:{}*****it:{}"
                        ,threadLocal.get(),inheritableThreadLocal.get());
                Future<?> submit = executorService.submit(() -> logger.info("!>t:{}*****it:{}*****pt:{}"
                        , threadLocal.get(), inheritableThreadLocal.get(), threadName));
                try {
                    submit.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(10);
        executorService.execute(()->logger.info("->t:{}*****it:{}"
                ,threadLocal.get(),inheritableThreadLocal.get()));
        TimeUnit.SECONDS.sleep(1000);
        executorService.shutdown();
    }
}
