package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 快速排序 n*log2n
 *
 * @author zhouxin
 * @since 2019/5/6
 */
public class QuickSort implements Sort {

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
        doSort(list, comparator, 0, list.size() - 1);
        this.result = list;
    }

    private <T> void doSort(List<T> list, Comparator<T> comparator, Integer start, Integer end) {
        if (end <= start) {
            return;
        }
        if (end - start == 1 && comparator.compare(list.get(end), list.get(start)) < 0) {
            swap(list, end, start);
            return;
        } else if (end - start == 1) {
            return;
        }
        T pivot = list.get(start);
        int i = start + 1, j = end;
        while (i <= j) {
            if (comparator.compare(pivot, list.get(i)) <= 0) {
                swap(list, i, j);
                j--;
            } else {
                i++;
            }
        }
        swap(list, start, j);
        doSort(list, comparator, start, j - 1);
        doSort(list, comparator, j + 1, end);
    }
}
