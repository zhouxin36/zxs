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
        for (int i = 0; i < 10000; i++) {
            int i1 = random.nextInt(9999);
            list1.add(i1);
        }
//        System.out.println(list1);
//        new QuickSortService4<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
        new QuickSortService2<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
        new QuickSortService7<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
//        new MergeSortService<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
//        new MergeSortService2<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
//        new HeapSortService6<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
//        new HeapSortService2<>(new ArrayList<>(list1), Integer::compareTo).sort().print();
    }
}
