package com.zx.codeanalysis.pattern.singleton.hungry;

/**
 * 优点：1、没有任何锁，执行效率高 2、线程安全
 *
 * 缺点：占用内存
 *
 * @author zhouxin
 * @date 2018/10/16
 */
public class Hungry {
    private Hungry(){}

    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }
}
