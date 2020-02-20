package com.zx.algorithm.sort.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/12/17
 */
public class HeapSortService2<T> extends AbstractSort<T> {

    private static final Logger logger = LoggerFactory.getLogger(HeapSortService2.class);
    private boolean show = true;

    public HeapSortService2(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size() - 1;
        for (int i = size / 2 - 1; i >= 0; i--) {
            doHeapSort(i, size);
        }
        while (size > 0){
            swap(0, size--);
            doHeapSort(0, size);
        }
        return this;
    }

    private void doHeapSort(int n, int size){
//        doWhileSort(n, size);
        doRecursiveSort(n, size);
    }

    private void doRecursiveSort(int n, int size) {
        if(show) {
            logger.info("递归堆排序");
            show = false;
        }
        int index = 2 * n + 1;
        if(index > size){
            return;
        }
        if(index < size && compare(index, index + 1) < 0){
            index++;
        }
        if(compare(n, index) >= 0){
            return;
        }
        swap(n, index);
        doRecursiveSort(index, size);
    }

    private void doWhileSort(int n, int size) {
        if(show) {
            logger.info("循环堆排序");
            show = false;
        }
        while (2 * n < size){
            int index = 2 * n + 1;
            if(index < size && compare(index, index + 1) < 0){
                index++;
            }
            if(compare(n, index) >= 0){
                return;
            }
            swap(n, index);
            n = index;
        }
    }
}
