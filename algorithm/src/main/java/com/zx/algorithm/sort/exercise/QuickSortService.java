package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;

/**
 * 快速排序 n*log2n
 *
 * @author zhouxin
 * @since 2019/12/5
 */
@SuppressWarnings("unused")
public class QuickSortService<T> extends AbstractSort<T> {

    public QuickSortService(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        List<T> source = getSource();
        doQuickSort(0, source.size() - 1);
        return this;
    }

    private void doQuickSort(int start, int end) {
        if (end - start == 1 && compare(start, end) > 0) {
            swap(start, end);
            return;
        } else if (end - start == 1) {
            return;
        }
        if (end <= start) {
            return;
        }
        makeQuick(start, end);
    }

    private void makeQuick(final int start, final int end) {
        int head = start + 1;
        int tag = end;
        int mid;
        while (head <= tag) {
            if (compare(start, head) <= 0) {
                swap(head, tag);
                tag--;
            } else {
                head++;
            }
        }
        swap(start, tag);
        doQuickSort(start, tag - 1);
        doQuickSort(tag + 1, end);
    }
}
