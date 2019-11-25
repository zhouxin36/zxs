package com.zx.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @author zhouxin
 * @date 2018/12/13
 */
public class CompletableFutureDemo {

  private static final Logger logger = LoggerFactory.getLogger(CompletableFutureDemo.class);

  public static void main(String[] args) {
    CompletableFuture.supplyAsync(() -> {
      logger.info("-------->thread:{},hello", Thread.currentThread().getName());
      return "hello";
    }).thenApply(str -> {
      logger.info("-------->thread:{},world", Thread.currentThread().getName());
      Integer i = null;
      logger.info("---->{}", i == 1);
      return str + " world";
    }).thenAccept(logger::info)
        .whenComplete((value, ex) -> {
          logger.info("---->任务完成:{}", value);
          logger.info("---->任务异常:{}", ex.getMessage());
        })
        .join()// 等待结束
    ;
    logger.info("------------->一切结束");
  }
}
