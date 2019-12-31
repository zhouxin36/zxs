package com.zx.concurrent.chapterone;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouxin
 * @date 2018/11/15
 */
public class AtomicDemo {

  private static volatile boolean a = true;
  private static boolean stop = false;

  public static void main(String[] args) throws Exception {
        test5();
  }

  /**
   * 停
   */
  private static void test1() throws InterruptedException {
    new Thread(() -> {
      while (a) {
        synchronized (AtomicDemo.class) {
        }
      }
    }).start();
    TimeUnit.SECONDS.sleep(1);
    a = false;
  }

  /**
   * 不停
   */
  private static void test2() throws InterruptedException {
    new Thread(() -> {
      while (!stop) {
      }
    }).start();
    TimeUnit.SECONDS.sleep(1);
    stop = true;
  }

  /**
   * 停
   */
  private static void test3() throws InterruptedException {
    new Thread(() -> {
      while (a) {
      }
    }).start();
    TimeUnit.SECONDS.sleep(1);
    a = false;
  }

  /**
   * 停
   */
  private static void test4() throws InterruptedException {
    new Thread(() -> {
      while (!stop) {
        synchronized (AtomicDemo.class) {
        }
      }
    }).start();
    TimeUnit.SECONDS.sleep(1);
    stop = true;
  }

  /**
   * 停
   */
  private static void test5() throws InterruptedException {
    new Thread(() -> {
      synchronized (AtomicDemo.class) {
        while (!stop) {
        }
      }
    }).start();
    TimeUnit.SECONDS.sleep(1);
    stop = true;
  }
}
