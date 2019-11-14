package com.zx.concurrent.chapterone;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouxin
 * @date 2018/11/15
 */
public class AtomicDemo {

    private volatile static boolean a = true;
    private static boolean stop = false;

    public static void main(String[] args) throws Exception{
//        test1();
        test2();
    }

    private static void test1() throws InterruptedException {
        new Thread(()->{
            while (a){
                synchronized (AtomicDemo.class){}
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        a = false;
    }
    private static void test2() throws InterruptedException {
        new Thread(()->{
            while (!stop){
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;
    }
}
