package com.zx.algorithm.sort;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @since 2019/5/11
 */
public enum SortEnum {

    SELECTION, INSERTION, BUBBLE, SHELL, HEAP, HEAP2, QUICK, QUICK2, MERGE, FORK_MERGE;

    private int dou = 1000;

    private static Logger logger = LoggerFactory.getLogger(SortEnum.class);

    public void switchSort(List<Integer> list) {
        AtomicInteger compareCount = new AtomicInteger(0);
        Comparator<Integer> comparator = (x, y) -> {
            compareCount.incrementAndGet();
            return x.compareTo(y);
        };
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        Sort sort = switchSort(list, comparator);
        logger.info("----->sort:{},compare:{},swap:{},time:{},list:{}"
                , StringUtils.rightPad(sort.getClass().getSimpleName(),16,'#')
                , StringUtils.rightPad(compareCount.get()+"",10,'#')
                , StringUtils.rightPad(sort.getSwapCount()+"",10,'#')
                , StringUtils.rightPad(stopwatch.getNanoTime() / dou+"",10,'#')
                , sort.getResult());
    }

    public <T> Sort switchSort(List<T> list, Comparator<T> comparator) {
        Sort sort;
        switch (this) {
            case SELECTION:
                sort = new SelectionSort();
                sort.sort(list, comparator);
                break;
            case BUBBLE:
                sort = new BubbleSort();
                sort.sort(list, comparator);
                break;
            case SHELL:
                sort = new ShellSort();
                sort.sort(list, comparator);
                break;
            case HEAP:
                sort = new HeapSort();
                sort.sort(list, comparator);
                break;
            case HEAP2:
                sort = new HeapSort2();
                sort.sort(list, comparator);
                break;
            case QUICK:
                sort = new QuickSort();
                sort.sort(list, comparator);
                break;
            case QUICK2:
                sort = new QuickSort2();
                sort.sort(list, comparator);
                break;
            case MERGE:
                sort = new MergeSort();
                sort.sort(list, comparator);
                break;
            case FORK_MERGE:
                sort = new ForkMergeSort();
                sort.sort(list, comparator);
                break;
            default:
                sort = new InsertionSort();
                sort.sort(list, comparator);
                break;
        }
        return sort;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
            logger.info("1");
        }, 1, 15, TimeUnit.SECONDS);
        Object o = scheduledFuture.get(10, TimeUnit.SECONDS);
    }
}
