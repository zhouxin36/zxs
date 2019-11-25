package com.zx.codeanalysis.spring.springv2.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhouxin
 * @date 2018/11/13
 */
public class ZXJDKAopProxy implements InvocationHandler {

  private ZXAopConfig aopConfig;

  private Object target;

  public void setAopConfig(ZXAopConfig aopConfig) {
    this.aopConfig = aopConfig;
  }

  public Object getInstance(Object target) {
    this.target = target;
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    ZXAopConfig.AspectPoint aspectPoint = aopConfig.get(method);
    if (aspectPoint != null) {
      aspectPoint.getPreMethod().invoke(aspectPoint.getAspect());
    }
    Object object = method.invoke(target, args);
    if (aspectPoint != null) {
      aspectPoint.getSuffixMethod().invoke(aspectPoint.getAspect());
    }
    return object;
  }
}
