package com.zx.microservice.microservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author zhouxin
 * @date 2018/11/30
 */
public class ObserverDemo {

    private static final Logger logger = LoggerFactory.getLogger(ObserverDemo.class);

    public static void main(String[] args) {

        MyObservable observable = new MyObservable();
        // 增加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                logger.info(value.toString());
            }
        });

        observable.setChanged();
        // 发布者通知，订阅者是被动感知（推模式）
        observable.notifyObservers("Hello,World");

//        echoIterator();

    }

    private static void echoIterator(){
        List<Integer> values = Arrays.asList(1,2,3,4,5);
        Iterator<Integer> integerIterator = values.iterator();
        while(integerIterator.hasNext()){ // 通过循环，主动去获取
            logger.info(integerIterator.next().toString());
        }
    }



    public static class MyObservable extends Observable {

        public void setChanged() {
            super.setChanged();
        }
    }
}
