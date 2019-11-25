package com.zx.codeanalysis.pattern.proxy.stat;

/**
 * @author zhouxin
 * @date 2018/10/17
 */
public class StaticTest {
  public static void main(String[] args) {
    new Father(new Son()).findLove();
  }
}
