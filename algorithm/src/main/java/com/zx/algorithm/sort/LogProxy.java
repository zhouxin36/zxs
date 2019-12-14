package com.zx.algorithm.sort;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhouxin
 * @since 2019/5/6
 */
public class LogProxy implements InvocationHandler {

  private final static Logger logger = LoggerFactory.getLogger(LogProxy.class);

  private Sort target;

  Sort getInstance(Sort target) {
    this.target = target;
    return (Sort) Proxy.newProxyInstance(LogProxy.class.getClassLoader(), new Class[]{Sort.class}, this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    StopWatch stopwatch = new StopWatch();
    stopwatch.start();
    method.invoke(target, args);
    logger.info("----->sort:{},swap:{},time:{}"
        , target.getClass().getSimpleName(), target.getSwapCount(), stopwatch.getNanoTime() / 1000);
    return null;
  }
}
