package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhouxin
 * @since 2019/12/19
 */
public class QuickSortService5<T> extends AbstractSort<T>{

    public static final int RETURN_STATE = -1;

    public QuickSortService5(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        doQuick(0, getSource().size() - 1);
//        doQuick3(0, getSource().size() - 1);
        return this;
    }

    private void doQuick(int start, int end) {
        int tag = quick(start, end);
        if(tag == RETURN_STATE){
            return;
        }
        doQuick(start, tag - 1);
        doQuick(tag + 1, end);
    }

    public int quick(int start, int end){
        if(end - start == 1 && compare(start, end) > 0){
            swap(start, end);
            return RETURN_STATE;
        }else if(end - start == 1 || end <= start){
            return RETURN_STATE;
        }
        int head = start;
        int tag = end + 1;
        while (true){
            while (compare(++head, start) < 0 && head != end){}
            while (compare(--tag, start) > 0 && tag != start){}
            if(head >= tag){
                break;
            }
            swap(head, tag);
        }
        swap(start, tag);
        return tag;
    }

    public void doQuick3(int start, int end){
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        stack.push(end);
        while (!stack.isEmpty()) {
            Integer en = stack.pop();
            Integer st = stack.pop();
            int tag = quick(st, en);
            if (tag == RETURN_STATE) {
                continue;
            }
            stack.push(st);
            stack.push(tag - 1);
            stack.push(tag + 1);
            stack.push(en);
        }
    }
}
