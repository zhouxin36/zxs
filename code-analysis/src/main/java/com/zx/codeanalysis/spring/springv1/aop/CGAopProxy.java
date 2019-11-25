package com.zx.codeanalysis.spring.springv1.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018/11/1
 */
public class CGAopProxy implements MyAopProxy, MethodInterceptor {

  private MyAopConfig config;

  public void setConfig(MyAopConfig config) {
    this.config = config;
  }

  public Object getProxy(Class<?> clazz) {
    Enhancer enhancer = new Enhancer();

    enhancer.setSuperclass(clazz);

    enhancer.setCallback(this);

    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    if (config.contains(method)) {
      MyAopConfig.MyAspect aspect = config.get(method);
      aspect.getPoints()[0].invoke(aspect.getAspect());
    }
    Object object = methodProxy.invokeSuper(o, objects);


    if (config.contains(method)) {
      MyAopConfig.MyAspect aspect = config.get(method);
      aspect.getPoints()[1].invoke(aspect.getAspect());
    }

    return object;
  }
}
