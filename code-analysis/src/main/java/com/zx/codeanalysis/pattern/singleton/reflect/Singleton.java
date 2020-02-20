package com.zx.codeanalysis.pattern.singleton.reflect;

import java.util.Arrays;

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
    /**
     * 防止反射创建实例
     */
    Arrays.stream(Thread.currentThread().getStackTrace())
            .filter(e -> "Singleton.java".equals(e.getFileName()))
            .filter(e -> "com.zx.codeanalysis.pattern.singleton.reflect.Singleton".equals(e.getClassName()))
            .filter(e -> "getInstance".equals(e.getMethodName()))
            .filter(e -> 33 == e.getLineNumber())
            .findAny().orElseThrow(() -> new UnsupportedOperationException("不支持反射调用"));
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