package com.zx.algorithm;

import java.lang.reflect.Constructor;

/**
 * @author zhouxin
 * @since 2020/1/16
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        MainTest m = MainTest.getInstance();
        System.out.println("new " + m);
        Constructor<MainTest> declaredConstructor = MainTest.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        MainTest mainTest = declaredConstructor.newInstance();
        System.out.println("反射" + mainTest);
    }
}
