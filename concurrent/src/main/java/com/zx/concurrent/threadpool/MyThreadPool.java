package com.zx.concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @since 2019/3/21
 */
public class MyThreadPool {

    private BlockingQueue<Runnable> blockingQueue;

    private List<Thread> threads = new ArrayList<>();

    private int maxCount;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public MyThreadPool(int maxCount, BlockingQueue<Runnable> blockingQueue) {
        this.maxCount = maxCount;
        this.blockingQueue = blockingQueue != null ? blockingQueue : new LinkedBlockingQueue<>();
    }

    public MyThreadPool() {
        this.maxCount = 1;
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    public void execute(Runnable runnable){
        if(maxCount > threads.size()){
            Thread thread = new Thread(() -> {
                Runnable first = runnable;
                while ((first != null) || (first = getTask()) != null){
                    try {
                        first.run();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        first = null;
                    }
                }
            },"Thread-"+atomicInteger.incrementAndGet());
            thread.start();
            threads.add(thread);
        }else {
            blockingQueue.offer(runnable);
        }
    }

    public Runnable getTask(){
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
