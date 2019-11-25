package com.zx.codeanalysis.spring.springv1.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhouxin
 * @date 2018/10/30
 */
public class DyAopProxy implements InvocationHandler, MyAopProxy {

  private Object target;

  private MyAopConfig config;

  public void setConfig(MyAopConfig config) {
    this.config = config;
  }

  public Object getProxy(Object target) {
    this.target = target;
    Class<?> clazz = target.getClass();
    return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    if (config.contains(method)) {
      MyAopConfig.MyAspect aspect = config.get(method);
      aspect.getPoints()[0].invoke(aspect.getAspect());
    }
    method.invoke(this.target, args);


    if (config.contains(method)) {
      MyAopConfig.MyAspect aspect = config.get(method);
      aspect.getPoints()[1].invoke(aspect.getAspect());
    }

    return null;
  }
}
