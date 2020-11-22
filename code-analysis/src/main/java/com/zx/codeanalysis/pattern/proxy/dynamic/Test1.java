package com.zx.codeanalysis.pattern.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class Test1 {

  private static final Logger logger = LoggerFactory.getLogger(Test1.class);

  public static void main(String[] args) {
//        PersonImp zhangsan = new Student("张三");

    InvocationHandler stuHandler = new StuInvocationHandler<>(Person.class,new PersonImpl());

    Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

    User user = stuProxy.giveMoney();

    logger.info("user:{}", user);
    stuHandler = new StuInvocationHandler<>(Person.class,stuProxy);

    stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

    logger.info("user:{}", stuProxy.giveMoney());
  }
}
