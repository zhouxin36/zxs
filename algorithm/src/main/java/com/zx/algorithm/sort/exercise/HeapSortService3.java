package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/12/18
 */
public class HeapSortService3<T> extends AbstractSort<T> {

    public HeapSortService3(List<T> source, Comparator<T> comparator) {
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

    private void swapHeap(int i, int size) {
        doSwapHeap2(i, size);
    }

    private void doSwapHeap(int i, int size) {
        while (2 * i < size){
            int left = 2 * i + 1;
            if(left < size && compare(left, left+1) < 0){
                left++;
            }
            if(compare(i, left) >= 0){
                break;
            }
            swap(i, left);
            i = left;
        }
    }

    private void doSwapHeap2(int i, int size) {
        int left = 2 * i + 1;
        if(left > size){
            return;
        }
        if(left < size && compare(left, left + 1) < 0){
            left++;
        }
        if(compare(i, left) >= 0){
            return;
        }
        swap(i, left);
        doSwapHeap2(left, size);
    }
}
