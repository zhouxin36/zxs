package com.zx.codeanalysis.pattern.proxy.nest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @since 2019/3/13
 */
public class ProxyNestCGClass {

  private static final Logger logger = LoggerFactory.getLogger(ProxyNestCGClass.class);

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    CGProxy target = new CGProxy();
    for (int i = 0; i < 10; i++) {
      MyProxyInvocation3 myProxyInvocation = new MyProxyInvocation3();
      target = (CGProxy) myProxyInvocation.getProxy(target, i);
      target.say();
      System.out.println("-----------------------------------------华丽的分界线-------------------------------------------");
    }
    long end = System.currentTimeMillis();
    logger.info("------------->运行时间，time:{}ms", end - start);
  }
}

class MyProxyInvocation3 implements MethodInterceptor {

  private final Logger logger = LoggerFactory.getLogger(MyProxyInvocation.class);

  private Object target;

  private int index;

  public Object getProxy(Object target, int index) {
    this.target = target;
    this.index = index;
    Enhancer enhancer = new Enhancer();
    enhancer.setCallback(this);
    Object tar = target;
    while (tar instanceof Factory) {
      MyProxyInvocation3 callback = (MyProxyInvocation3) ((Factory) tar).getCallback(0);
      tar = callback.getTarget();
    }
    enhancer.setSuperclass(tar.getClass());
    return enhancer.create();
  }

  public Object getTarget() {
    return target;
  }

  @Override
  public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
    Object value;
    if (Object.class.equals(method.getDeclaringClass())) {
      value = method.invoke(target, args);
      return value;
    }
    logger.info("---------->开始,index:{}", index);
    value = method.invoke(target, args);
//        value = methodProxy.invokeSuper(o,args);
    logger.info("---------->结束,index:{}", index);
    return value;
  }
}
