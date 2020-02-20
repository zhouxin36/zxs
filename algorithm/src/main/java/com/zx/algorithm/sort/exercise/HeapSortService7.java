package com.zx.algorithm.sort.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/1/6
 */
public class HeapSortService7 extends AbstractSort<Integer> {

    private final static Logger logger = LoggerFactory.getLogger(HeapSortService7.class);

    public HeapSortService7(List<Integer> source, Comparator<Integer> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<Integer> doSort() {
        int size = getSource().size() - 1;
        for (int i = size / 2 - 1; i >= 0; i--) {
            swapHead(i, size);
        }
        while (size > 0) {
            swap(0, size--);
            swapHead(0, size);
        }
        return this;
    }

    public void swapHead(int i, int size) {
//        doHead1(i, size);
        doHead2(i, size);
    }

    private void doHead2(int i, int size) {
        int left = 2 * i + 1;
        if (left > size) {
            return;
        }
        if (left < size && compare(left, left + 1) < 0) {
            left++;
        }
        if (compare(i, left) >= 0) {
            return;
        }
        swap(i, left);
        doHead2(left, size);
    }

    private void doHead1(int i, int size) {
        while (2 * i < size) {
            int left = 2 * i + 1;
            if (left < size && compare(left, left + 1) < 0) {
                left++;
            }
            if (compare(i, left) >= 0) {
                return;
            }
            swap(i, left);
            i = left;
        }
    }

    public ISort<Integer> method(Object o, Object o2) {
        Integer n = (Integer) o;
        int head = 0;
        int tag = getSource().size() - 1;
        if(o2 == null) {
            int tmp;
            for (tmp = (tag - head) / 2; ; tmp = head + (tag - head) / 2) {
                if (tmp >= getSource().size()) {
                    logger.info("出错啦啦啦啦啦啦");
                }
                if (compare(tmp, n) <= 0 && compare(tmp + 1, n) > 0) {
                    break;
                } else if (compare(tmp, n) <= 0) {
                    head = tmp;
                } else {
                    tag = tmp;
                }
            }
            head = 0;
            tag = tmp;
        }
        while (head < tag) {
            if (add(head, tag) < n) {
                head++;
            } else if (add(head, tag) > n) {
                tag--;
            } else {
                head++;
                logger.info("head:{}, tag:{}, {} + {} = {}", head, tag, getSource().get(head), getSource().get(tag), n);
            }
        }
        return this;
    }
}
