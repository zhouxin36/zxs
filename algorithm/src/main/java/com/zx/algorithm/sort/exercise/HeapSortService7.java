package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/1/6
 */
public class HeapSortService7<T> extends AbstractSort<T> {

    public HeapSortService7(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size() - 1;
        for (int i = size / 2 - 1; i >= 0; i--) {
            swapHead(i, size);
        }
        while (size > 0){
            swap(0, size--);
            swapHead(0, size);
        }
        return this;
    }

    public void swapHead(int i, int size){
//        doHead1(i, size);
        doHead2(i, size);
    }

    private void doHead2(int i, int size) {
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
        doHead2(left, size);
    }

    private void doHead1(int i, int size) {
        while (2 * i < size){
            int left = 2 * i + 1;
            if(left < size && compare(left, left + 1) < 0){
                left++;
            }
            if(compare(i, left) >= 0){
                return;
            }
            swap(i, left);
            i = left;
        }
    }
}
