package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/1/3
 */
public class HeapSortService6<T> extends AbstractSort<T> {

    public HeapSortService6(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size() - 1;
        for (int i = size / 2 - 1; i >= 0; i--) {
            swapHeap(i, size);
        }
        while (size > 0){
            swap(0, size--);
            swapHeap(0, size);
        }
        return this;
    }

    public void swapHeap(int start, int size){
        whileHeap(start, size);
//        stackHeap(start, size);
    }

    private void stackHeap(int start, int size) {
        if(start * 2 >= size){
            return;
        }
        int left = start * 2 + 1;
        if(left < size && compare(left, left + 1) < 0){
            left++;
        }
        if(compare(start, left) >= 0){
            return;
        }
        swap(start, left);
        stackHeap(left, size);
    }

    private void whileHeap(int start, int size) {
        while (2 * start < size){
            int left = 2 * start + 1;
            if (left <size && compare(left, left + 1) < 0){
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
