package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/12/20
 */
public class HeapSortService5<T> extends AbstractSort<T> {

    public HeapSortService5(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size() - 1;
        for(int i = size / 2 - 1; i >= 0; i--){
            doSwapHeap(i, size);
        }
        while (size > 0){
            swap(0, size--);
            doSwapHeap(0, size);
        }
        return this;
    }

    private void doSwapHeap(int start, int size){
        doWhileSwapHeap(start, size);
//        doStackSwapHeap(start, size);
    }

    private void doStackSwapHeap(int start, int size) {
        int left = 2 * start + 1;
        if(left > size){
            return;
        }
        if (left < size && compare(left, left + 1) < 0){
            left++;
        }
        if(compare(start, left) >= 0){
            return;
        }
        swap(start, left);
        doStackSwapHeap(left, size);
    }

    private void doWhileSwapHeap(int start, int size) {
        while (2 * start < size){
            int left = 2* start + 1;
            if (left < size && compare(left, left + 1) < 0){
                left++;
            }
            if(compare(start, left) >= 0){
                break;
            }
            swap(start, left);
            start = left;
        }
    }
}
