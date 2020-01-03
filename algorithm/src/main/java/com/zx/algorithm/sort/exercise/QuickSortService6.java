package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/1/3
 */
public class QuickSortService6<T> extends AbstractSort<T> {

    private static final Integer RETURN_STATE = -1;
    public QuickSortService6(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size();
        doQuick(0, size - 1);
        return this;
    }

    private void doQuick(int start, int end) {
        doStackQuick(start, end);
//        doRecursionQuick(start, end);
    }

    private void doStackQuick(int start, int end) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        stack.push(end);
        while (!stack.isEmpty()){
            Integer end1 = stack.pop();
            Integer start1 = stack.pop();
            int tag = quick(start1, end1);
            if(tag == RETURN_STATE){
                continue;
            }
            stack.push(start1);
            stack.push(tag - 1);
            stack.push(tag + 1);
            stack.push(end1);
        }
    }

    private void doRecursionQuick(int start, int end) {
        int tag = quick(start, end);
        if(tag == RETURN_STATE){
            return;
        }
        doRecursionQuick(start, tag - 1);
        doRecursionQuick(tag + 1, end);
    }

    private int quick(int start, int end){
        if(end - start == 1 && compare(start, end) > 0){
            swap(start, end);
            return RETURN_STATE;
        }else if(end < start + 2){
            return RETURN_STATE;
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
        return tag;
    }
}
