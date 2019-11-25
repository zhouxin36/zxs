package com.zx.codeanalysis.pattern.proxy.cglib;

/**
 * --add-opens java.base/java.lang=cglib
 *
 * @author zhouxin
 * @date 2018/10/17
 */
public class CglibTest {

  public static void main(String[] args) {
    PersonImp instance = (PersonImp) new CglibProxy().getInstance(PersonImp.class);
    instance.findLove();
  }
}
