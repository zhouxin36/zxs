package com.zx.algorithm;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2019/5/9
 */
public class MainTest {

    private String str;

    private MainTest() {
        Arrays.stream(Thread.currentThread().getStackTrace())
                .filter(e -> "MainTest.java".equals(e.getFileName()))
                .filter(e -> "com.zx.algorithm.MainTest".equals(e.getClassName()))
                .filter(e -> "getInstance".equals(e.getMethodName()))
                .filter(e -> 27 == e.getLineNumber())
                .findAny().orElseThrow(() -> new UnsupportedOperationException("不支持反射调用"));
        this.str = "1";
    }

    public static MainTest getInstance() {
        return new MainTest();
    }


}
