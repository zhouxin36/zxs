package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 归并排序 n*log2n
 *
 * @author zhouxin
 * @since 2019/5/4
 */
public class ForkMergeSort implements Sort {

    private AtomicInteger swapCount = new AtomicInteger(0);

    private List result;

    @Override
    public List getResult() {
        return result;
    }

    @Override
    public int getSwapCount() {
        return swapCount.get();
    }

    @Override
    public void incrementSwapCount() {
        swapCount.incrementAndGet();
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {
        //noinspection unchecked
        ForkJoinMergeSort<T> forkJoinMergeSort = new ForkJoinMergeSort<>(list, (T[])new Object[list.size()], comparator, 0, list.size() - 1);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(forkJoinMergeSort);
        this.result = list;
    }

    class ForkJoinMergeSort<T> extends RecursiveAction {

        private List<T> list;

        private Comparator<T> comparator;

        private T[] swapArray;

        private int start;

        private int end;

        ForkJoinMergeSort(List<T> list, T[] swapArray, Comparator<T> comparator, int start, int end) {
            this.list = list;
            this.swapArray = swapArray;
            this.comparator = comparator;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (start >= end)
                return;
            int mid = start + (end - start) / 2;
            invokeAll(new ForkJoinMergeSort<>(list, swapArray, comparator, start, mid)
                    , new ForkJoinMergeSort<>(list, swapArray, comparator, mid + 1, end));
            MergeSort.doMerge(list, swapArray, comparator, start, mid, end);
        }
    }
}