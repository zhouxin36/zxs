package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 堆排序 n*log2n
 *
 * @author zhouxin
 * @since 2019/5/7
 */
public class HeapSort implements Sort {

    private int len;

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
        len = list.size();
        buildHeap(list, comparator);
        while (len > 0) {
            swap(list,len - 1,0);
            len--;
            swapHeap(list, comparator, 0);
        }
        this.result = list;
    }

    private <T> void buildHeap(List<T> list, Comparator<T> comparator) {
        for (int i = (len - 1) / 2; i >= 0; i--) {
            swapHeap(list, comparator, i);
        }
    }

    private <T> void swapHeap(List<T> list, Comparator<T> comparator, int parentsNode) {
        int max = parentsNode;
        int left = 2 * parentsNode + 1;
        int right = 2 * parentsNode + 2;
        if (left < len && comparator.compare(list.get(max), list.get(left)) < 0) {
            max = left;
        }
        if (right < len && comparator.compare(list.get(max), list.get(right)) < 0) {
            max = right;
        }
        if (parentsNode == max) {
            return;
        }
        swap(list,parentsNode,max);
        swapHeap(list, comparator, max);

    }
}
