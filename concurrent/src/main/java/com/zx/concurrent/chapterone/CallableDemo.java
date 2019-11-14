package com.zx.concurrent.chapterone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author zhouxin
 * @date 2018/11/15
 */
public class CallableDemo implements Callable<String> {

    private static final Logger logger = LoggerFactory.getLogger(CallableDemo.class);

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        doThread();
        doThread();
        doThread();
        executorService.shutdown();
    }

    private static void doThread() throws InterruptedException, ExecutionException {
        Future<String> submit1 = executorService.submit(new CallableDemo());
        Future<String> submit2 = executorService.submit(new CallableDemo());
        Future<String> submit3 = executorService.submit(new CallableDemo());
        logger.error(submit1.get());
        logger.error(submit2.get());
        logger.error(submit3.get());
    }

    @Override
    public String call() throws Exception {
        logger.error("------------->call");
        return "String";
    }
}
