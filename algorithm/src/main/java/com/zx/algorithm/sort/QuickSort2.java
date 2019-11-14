package com.zx.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @since 2019/6/8
 */
public class QuickSort2 implements Sort {

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
        doSort(list,comparator,0,list.size()-1);
        this.result = list;
    }

    private <T> void doSort(List<T> list, Comparator<T> comparator, int start, int end) {
        if(end - start == 1){
            swap(list, start, end);
            return;
        }
        if(end <= start){
            return;
        }
        int a = start + 1;
        int b = end;
        while (a <= b){
            if(comparator.compare(list.get(start), list.get(a)) <= 0){
                swap(list, a, b);
                b--;
            }else {
                a++;
            }
        }
        swap(list, start, b);
        doSort(list, comparator, start, b - 1);
        doSort(list, comparator, b + 1, end);
    }
}
