package com.zx.algorithm.sort.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author zhouxin
 * @since 2019/12/17
 */
@SuppressWarnings("DuplicatedCode")
public class QuickSortService2<T> extends AbstractSort<T> {

    private static final Logger logger = LoggerFactory.getLogger(QuickSortService2.class);

    private static final Integer RETURN_STATUS = -1;

    private boolean show = true;

    public QuickSortService2(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        List<T> source = getSource();
        doQuickSort(0, source.size() - 1);
        return this;
    }

    private void doQuickSort(int start, int end) {
//        doRecursiveSort(start, end);
        doWhileSort(start, end);
    }

    private void doWhileSort(int start, int end) {
        if(show) {
            logger.info("循环堆排序");
            show = false;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        stack.push(end);
        while (!stack.isEmpty()){
            int e = stack.pop();
            int s = stack.pop();
            int tag = makeSort(s, e);
            if(tag == RETURN_STATUS){
                continue;
            }
            stack.push(s);
            stack.push(tag - 1);
            stack.push(tag + 1);
            stack.push(e);
        }
    }

    private void doRecursiveSort(int start, int end) {
        if(show) {
            logger.info("递归堆排序");
            show = false;
        }
        int tag = makeSort(start, end);
        if(tag == RETURN_STATUS){
            return;
        }
        doRecursiveSort(start, tag - 1);
        doRecursiveSort(tag + 1, end);
    }

    private int makeSort(int start, int end) {
       if(end - start == 1 && compare(start, end) > 0){
           swap(start, end);
           return RETURN_STATUS;
       } else if(end < start + 2){
           return RETURN_STATUS;
       }
       int head = start;
       int tag = end + 1;
       while (true){
           while (compare(start, ++head) >= 0 && head < end){}
           while (compare(start, --tag) <= 0 && start < tag){}
           if(head >= tag){
               break;
           }
           swap(head, tag);
       }
       swap(start, tag);
       return tag;
    }
}
