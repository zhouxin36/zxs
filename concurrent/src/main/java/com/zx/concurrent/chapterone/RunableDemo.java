package com.zx.concurrent.chapterone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouxin
 * @date 2018/11/15
 */
public class RunableDemo implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(RunableDemo.class);

  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    for (int i = 0; i < 100; i++) {
      doThread();
    }
    executorService.shutdown();
  }

  private static void doThread() {
    executorService.submit(new RunableDemo());
    executorService.submit(new RunableDemo());
    executorService.submit(new RunableDemo());
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    logger.error("------------->call");
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Integer i = null;
    logger.info("---->{}", i == 1);
    logger.info("---->结束");

  }
}
