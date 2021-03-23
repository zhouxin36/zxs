package com.zx.jdkanalysis.动态追踪技术;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouxin
 * @since 2021/3/18
 */
public class Base {
    public void process(){
        System.out.println("process");
    }

    public static void main(String[] args) {
        Base base = new Base();
        while (true){
            base.process();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}