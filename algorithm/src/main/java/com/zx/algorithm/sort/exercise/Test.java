package com.zx.algorithm.sort.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author zhouxin
 * @since 2019/12/17
 */
public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 9999; i++) {
            int i1 = random.nextInt(9999);
            list1.add(i1);
        }
//        System.out.println(list1);
        new QuickSortService3<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
        new QuickSortService4<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
        new QuickSortService5<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
//        new HeapSortService<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
//        new HeapSortService4<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
//        new HeapSortService5<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
    }
}
