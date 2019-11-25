package com.zx.codeanalysis.pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018/10/17
 */
public class CglibProxy2 implements MethodInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(CglibProxy2.class);

  private Object target;

  public Object getInstance(Object target) {
    this.target = target;
    Enhancer enhancer = new Enhancer();

    enhancer.setSuperclass(target.getClass().getInterfaces()[0]);

    enhancer.setCallback(this);

    return enhancer.create();
  }

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    //业务
    logger.info("前");
    Object invoke = method.invoke(target, args);
    logger.info("后");

    return invoke;
  }
}
