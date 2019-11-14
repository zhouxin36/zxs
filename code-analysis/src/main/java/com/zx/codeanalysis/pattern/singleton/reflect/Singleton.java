package com.zx.codeanalysis.pattern.singleton.reflect;

/**
 * @author zhouxin
 * @since 2019/3/12
 */
public class Singleton {

    private volatile static Singleton uniqueSingleton;

    private final String str1 = "1";

    private final static String str2 = "2";

    private String str3 = "3";

    private static String str4 = "4";

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