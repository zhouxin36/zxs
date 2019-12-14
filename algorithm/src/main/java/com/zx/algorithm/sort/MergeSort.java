package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 归并排序 n*log2n
 *
 * @author zhouxin
 * @since 2019/5/4
 */
public class MergeSort implements Sort {

    private AtomicInteger swapCount = new AtomicInteger(0);

    private List result;

    private Object[] swapArray;

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
        swapArray = new Object[list.size()];
        doSort(list, comparator, 0, list.size() - 1);
        this.result = list;
    }

    private <T> void doSort(List<T> list, Comparator<T> comparator, int start, int end) {
        if (start >= end)
            return;
        int mid = start + (end - start) / 2;
        doSort(list, comparator, start, mid);
        doSort(list, comparator, mid + 1, end);
        //noinspection unchecked
        doMerge(list, (T[]) swapArray, comparator, start, mid, end);
    }

    static <T> void doMerge(List<T> list, T[] swapArray, Comparator<T> comparator, int start, int mid, int end) {
        for (int i = start; i < end + 1; i++) {
            swapArray[i] = list.get(i);
        }
        int a = start, b = mid + 1;
        for (int i = start; i < end + 1; i++) {
            if (a > mid) {
                list.set(i, swapArray[b]);
                b++;
            } else if (b > end) {
                list.set(i, swapArray[a]);
                a++;
            } else if (comparator.compare(swapArray[a], swapArray[b]) > 0) {
                list.set(i, swapArray[b]);
                b++;
            } else {
                list.set(i, swapArray[a]);
                a++;
            }
        }
    }
}
