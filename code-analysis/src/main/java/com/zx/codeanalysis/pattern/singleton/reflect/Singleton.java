package com.zx.codeanalysis.pattern.singleton.reflect;

/**
 * @author zhouxin
 * @since 2019/3/12
 */
public class Singleton {

  private static final String str2 = "2";
  private static volatile Singleton uniqueSingleton;
  private static String str4 = "4";
  private final String str1 = "1";
  private String str3 = "3";

  private Singleton() {
  }

  public static Singleton getInstance() {
    if (null == uniqueSingleton) {
      synchronized (Singleton.class) {
        if (null == uniqueSingleton) {
          uniqueSingleton = new Singleton();
        }
      }
    }
    return uniqueSingleton;
  }
}