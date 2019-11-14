package com.zx.concurrent.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/5/15
 */
public class MyThreadPoolTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyThreadPoolTest.class);

  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();


  public static void main(String[] args)
      throws InterruptedException, TimeoutException, ExecutionException {
    test();
//        MyScheduledPool myScheduledPool = new MyScheduledPool(2, 5, TimeUnit.SECONDS);
//        FutureTask execute = myScheduledPool.execute(() -> {
//            logger.info("1");
//        });
//        TimeUnit.SECONDS.sleep(1000);
  }

  private static void test() throws InterruptedException {
    MyThreadPool executorService = new MyThreadPool(5, null);
    threadLocal.set("threadLocal");
    inheritableThreadLocal.set("inheritableThreadLocal");
    AtomicInteger atomicInteger = new AtomicInteger(0);
    for (int i = 0; i < 15; i++) {
      TimeUnit.SECONDS.sleep(1);
      executorService.execute(() -> {
        System.out.println(
            "-------------------------------------华丽的分割线---------------------------------------");
        LOGGER.info("->t:{}*****it:{}"
            , threadLocal.get(), inheritableThreadLocal.get());
        final int t = atomicInteger.incrementAndGet();
        final int it = atomicInteger.incrementAndGet();
        threadLocal.set(t + "");
        inheritableThreadLocal.set(it + "");
        String threadName = Thread.currentThread().getName();
        LOGGER.info("^>t:{}*****it:{}"
            , threadLocal.get(), inheritableThreadLocal.get());
        executorService.execute(() -> LOGGER.info("!>t:{}*****it:{}*****pt:{}"
            , threadLocal.get(), inheritableThreadLocal.get(), threadName));
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
    TimeUnit.SECONDS.sleep(10);
    executorService.execute(() -> LOGGER.info("->t:{}*****it:{}"
        , threadLocal.get(), inheritableThreadLocal.get()));
    TimeUnit.SECONDS.sleep(1000);
  }
}
