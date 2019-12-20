package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/12/17
 */
@SuppressWarnings("DuplicatedCode")
public class QuickSortService2<T> extends AbstractSort<T> {


    public QuickSortService2(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        List<T> source = getSource();
        makeSort(0, source.size() - 1);
        return this;
    }

    private void makeSort(int start, int end) {
        if (end - start == 1 && compare(start, end) > 0) {
            swap(start, end);
            return;
        } else if (end - start == 1) {
            return;
        }
        if (end <= start) {
            return;
        }
        int head = start;
        int tag = end + 1;
        while (true) {
            while (compare(start, ++head) >= 0) {
                if (head == end) {
                    break;
                }
            }
            while (compare(start, --tag) < 0) {
                if (tag == start) {
                    break;
                }
            }
            if (head >= tag) {
                break;
            }
            swap(head, tag);
        }
        swap(start, tag);
        makeSort(start, tag - 1);
        makeSort(tag + 1, end);
    }
}
