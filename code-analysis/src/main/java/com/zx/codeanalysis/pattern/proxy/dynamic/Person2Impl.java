package com.zx.codeanalysis.pattern.proxy.dynamic;

/**
 * @author zhouxin
 * @since 2019/2/20
 */
public class Person2Impl implements Person {
  @Override
  public User giveMoney() {
    return new User("1","1");
  }
}
