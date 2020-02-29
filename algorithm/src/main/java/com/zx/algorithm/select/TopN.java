package com.zx.algorithm.select;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author zhouxin
 * @since 2020/2/25
 */
@SuppressWarnings("DuplicatedCode")
public class TopN {

    @SuppressWarnings("ComparatorCombinators")
    public static void main(String[] args) {
        int length = 100;
        int n = 7;
        Random random = new Random();
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(insertSort(array.clone(), 0, array.length - 1, (a, b) -> a - b)));
        System.out.println(Arrays.toString(array.clone()));
        System.out.println(Arrays.toString(top(array.clone(), n, (a, b) -> a - b)));

    }

    private static Integer[] top(Integer[] array, int n, Comparator<Integer> comparator) {
        if (array == null || array.length < n) {
            throw new IllegalArgumentException("非法参数");
        }
        Integer[] result = new Integer[n];
        for (int i : array) {
            balanceHeap(result, i, comparator);
        }
        return insertSort(result, 0, result.length - 1, comparator);
    }

    private static void balanceHeap(Integer[] array, int a, Comparator<Integer> comparator) {
        if (array[0] != null && comparator.compare(array[0], a) >= 0) {
            return;
        }
        array[0] = a;
        int size = 0;
        int length = array.length - 1;
        while (2 * size < length) {
            int index = 2 * size + 1;
            if (array[index] == null) {
                swap(array, size, index);
                size = index;
                continue;
            }
            if (index < length && array[index + 1] == null) {
                swap(array, size, index + 1);
                size = index + 1;
                continue;
            }
            if (index < length && comparator.compare(array[index], array[index + 1]) > 0) {
                index++;
            }
            if (comparator.compare(array[size], array[index]) <= 0) {
                break;
            }
            swap(array, size, index);
            size = index;
        }

    }

    private static void swap(Integer[] array, int a, int b) {
        Integer tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private static Integer[] insertSort(Integer[] array, int start, int end, Comparator<Integer> comparator) {
        if (end - start < 2 || array.length <= end) {
            return array;
        }
        for (int i = start + 1; i <= end; i++) {
            for (int j = i; j > start && comparator.compare(array[j - 1], array[j]) > 0; j--) {
                int tmp = array[j - 1];
                array[j - 1] = array[j];
                array[j] = tmp;
            }
        }
        return array;
    }
}
