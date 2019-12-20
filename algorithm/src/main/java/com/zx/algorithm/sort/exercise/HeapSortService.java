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
        int size = source.size() - 1;
        buildHeap(size);
        while (size > 0) {
            swap(0, size--);
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
        if(left > size){
            return;
        }
        if (left < size && compare(left, left + 1) < 0) {
            left++;
        }
        if (compare(i, left) >= 0) {
            return;
        }
        swap(i, left);
        swapHeap(left, size);
    }
//    private void swapHeap(int i, int size) {
//        while (2 * i < size) {
//            int left = 2 * i + 1;
//            if (left < size && compare(left, left + 1) < 0) {
//                left++;
//            }
//            if (compare(i, left) >= 0) {
//                break;
//            }
//            swap(i, left);
//            i = left;
//        }
//    }

}
