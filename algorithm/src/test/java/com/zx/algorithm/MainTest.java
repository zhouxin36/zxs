package com.zx.algorithm;

/**
 * @author zhouxin
 * @since 2019/5/9
 */
public class MainTest {

    private static ThreadLocal<String> th = new ThreadLocal<>();
    public static void main(String[] args) {

        th.set("aaa");
        System.out.println();
    }
}
