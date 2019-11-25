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
public class CglibProxy implements MethodInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(CglibProxy.class);

  public Object getInstance(Class<?> clazz) {
    Enhancer enhancer = new Enhancer();

    enhancer.setSuperclass(clazz);

    enhancer.setCallback(this);

    return enhancer.create();
  }

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    //业务
    logger.info("前");
    Object o = proxy.invokeSuper(obj, args);
    logger.info("后");

    return null;
  }
}
