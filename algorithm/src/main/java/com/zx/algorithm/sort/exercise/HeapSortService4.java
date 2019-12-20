package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/12/19
 */
public class HeapSortService4<T> extends AbstractSort<T> {

    public HeapSortService4(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size() - 1;
        for (int i = size / 2 + 1; i >= 0; i--) {
            doSwap(i, size);
        }
        while (size > 0){
            swap(0, size--);
            doSwap(0, size);
        }
        return this;
    }

    private void doSwap(int i, int size){
//        swapHeap(i, size);
        swapHeap2(i, size);
    }

    private void swapHeap(int i, int size){
        int j = 2 * i + 1;
        if(j > size){
            return;
        }
        if(j < size && compare(j, j + 1) < 0){
            j++;
        }
        if(compare(i, j) >= 0){
            return;
        }
        swap(i, j);
        swapHeap(j, size);
    }

    private void swapHeap2(int i, int size){
        while (2 * i < size){
            int j = 2 * i + 1;
            if(j < size && compare(j, j + 1) < 0){
                j++;
            }
            if(compare(i, j) >= 0){
                break;
            }
            swap(i, j);
            i = j;
        }
    }
}
