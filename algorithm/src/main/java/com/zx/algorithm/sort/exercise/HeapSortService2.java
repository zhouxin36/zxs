package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/12/17
 */
public class HeapSortService2<T> extends AbstractSort<T> {

    public HeapSortService2(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int n = getSource().size() - 1;
        for (int k = n/2 - 1; k >= 0; k--)
            sink(k, n);
        while (n > 0) {
            swap(0, n--);
            sink(0, n);
        }
        return this;
    }

    private void sink( int k, int n) {
        while (2*k < n) {
            int j = 2*k + 1;
            if (j < n && compare(j, j + 1) < 0) j++;
            if (compare(k, j) >= 0) break;
            swap(k , j);
            k = j;
        }
    }
}
