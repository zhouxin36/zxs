package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/1/7
 */
public class InsertionSortService2<T> extends AbstractSort<T> {

    public InsertionSortService2(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size();
        if(size < 2){
            return this;
        }
        for (int i = 1; i < size; i++) {
            int j = i - 1;
            while (j >= 0 && compare(j + 1, j) < 0){
                swap(j, j + 1);
                j--;
            }
        }
        return this;
    }
}
