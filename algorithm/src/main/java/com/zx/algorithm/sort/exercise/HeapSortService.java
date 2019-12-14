package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * 堆排序 n*log2n
 *
 * @author zhouxin
 * @since 2019/12/7
 */
@SuppressWarnings("unused")
public class HeapSortService<T> extends AbstractSort<T> {

    public HeapSortService(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        List<T> source = getSource();
        int size = source.size();
        buildHeap(size);
        while (size > 0) {
            swap(0, size - 1);
            size--;
            swapHeap(0, size);
        }
        return this;
    }

    private void buildHeap(int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            swapHeap(i, size);
        }
    }

    private void swapHeap(int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left < size && compare(max, left) < 0) {
            max = left;
        }
        if (right < size && compare(max, right) < 0) {
            max = right;
        }
        if (i == max) {
            return;
        }
        swap(i, max);
        swapHeap(max, size);
    }

}
