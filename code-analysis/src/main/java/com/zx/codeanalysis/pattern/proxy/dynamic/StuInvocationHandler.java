package com.zx.codeanalysis.pattern.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.StringJoiner;

public class StuInvocationHandler<T> implements InvocationHandler {

  private static final Logger logger = LoggerFactory.getLogger(StuInvocationHandler.class);
  /**
   * invocationHandler持有的被代理对象
   */
  private final Class<T> mapperInterface;

  public StuInvocationHandler(Class<T> mapperInterface) {
    this.mapperInterface = mapperInterface;
  }

  /**
   * proxy:代表动态代理对象
   * method：代表正在执行的方法
   * args：代表调用目标方法时传入的实参
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    logger.info("代理执行{}方法", method.getName());
    logger.info("Object.class:{}", Object.class);
    logger.info("method.getDeclaringClass:{}", method.getDeclaringClass());
    if (Object.class.equals(method.getDeclaringClass())) {
      return method.invoke(this, args);
    }
    logger.info("<>interface:{}" + mapperInterface.toGenericString());
    return new User("123", "zhouxin");
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", StuInvocationHandler.class.getSimpleName() + "[", "]")
        .add("mapperInterface=" + mapperInterface)
        .toString();
  }
}