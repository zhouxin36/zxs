package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/12/19
 */
public class QuickSortService4<T> extends AbstractSort<T>{

    public QuickSortService4(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        doQuick(0, getSource().size() - 1);
        return this;
    }

    private void doQuick(int start, int end) {
        if(end - start == 1 && compare(start, end) > 0){
            swap(start, end);
            return;
        } else if(end - start == 1){
            return;
        }
        if(start >= end){
            return;
        }
        int head = start;
        int tag = end + 1;
        while (true){
            while (compare(start, ++head) > 0 && head != end){}
            while (compare(start, --tag) < 0 && tag != start){}
            if(head >= tag){
                break;
            }
            swap(head, tag);
        }
        swap(start, tag);
        doQuick(start, tag - 1);
        doQuick(tag + 1, end);
    }
}
