package com.zx.algorithm.sort.exercise;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/1/6
 */
public class QuickSortService7<T> extends AbstractSort<T> {


    public static final int RETURN_STATUS = -1;
    public QuickSortService7(List<T> source, Comparator<T> comparator) {
        super(source, comparator);
    }

    @Override
    protected ISort<T> doSort() {
        int size = getSource().size() - 1;
        quick(0, size);
        return this;
    }

    private void quick(int start, int end) {
//        quick1(start, end);
        quick2(start, end);
    }

    private void quick2(int start, int end) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        stack.push(end);
        while (!stack.isEmpty()){
            int e = stack.pop();
            int s = stack.pop();
            int tag = doQuick(s, e);
            if(tag == RETURN_STATUS){
                continue;
            }
            stack.push(s);
            stack.push(tag - 1);
            stack.push(tag + 1);
            stack.push(e);
        }
    }

    private void quick1(int start, int end) {
        int tag = doQuick(start, end);
        if(tag == RETURN_STATUS){
            return;
        }
        quick1(start, tag - 1);
        quick1(tag + 1, end);

    }

    public int doQuick(int start, int end){
        if(end - start == 1 && compare(start, end) > 0){
            swap(start, end);
            return RETURN_STATUS;
        }else if(end < start + 2){
            return RETURN_STATUS;
        }
        int head = start;
        int tag = end + 1;
        while (true){
            while (compare(start, ++head) >= 0 && head < end){}
            while (compare(start, --tag) <= 0 && tag > start){}
            if(head >= tag){
                break;
            }
            swap(head, tag);
        }
        swap(start, tag);
        return tag;
    }

}
