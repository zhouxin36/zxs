package com.zx.algorithm.sort;

import com.zx.algorithm.HeapQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.zx.algorithm.sort.SortEnum.*;

/**
 * @author zhouxin
 * @since 2019/4/28
 */
public class Main {

    private static final List<Integer> list1 = new ArrayList<>();
    private static final List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list;
        HeapQueue<Integer> heapQueue = new HeapQueue<>(8);
        for (int i = 1000; i > 0; i--) {
            int i1 = random.nextInt(2000);
            list1.add(i1);
            heapQueue.insert(i1);
//            list2.add(i);
        }
        list = list1;
//        INSERTION.switchSort(new ArrayList<>(list));
//        SELECTION.switchSort(new ArrayList<>(list));
//        BUBBLE.switchSort(new ArrayList<>(list));
//        MERGE.switchSort(new ArrayList<>(list));
//        FORK_MERGE.switchSort(new ArrayList<>(list));
//        QUICK.switchSort(new ArrayList<>(list));
//        QUICK2.switchSort(new ArrayList<>(list));
        HEAP.switchSort(new ArrayList<>(list));
        HEAP2.switchSort(new ArrayList<>(list));
        for (int i = 0; heapQueue.getSize() > 0; i++) {
            System.out.print(" ,"+heapQueue.deleteMin());
        }
    }

}
