package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 冒泡排序 O(n*n)
 *
 * @author zhouxin
 * @since 2019/4/29
 */
public class BubbleSort implements Sort {

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
        boolean change;
        for (int i = 0; i < list.size(); i++) {
            change = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
        this.result = list;
    }

}
