package com.zx.algorithm.select;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 选择出第i大（小）的数
 *
 * @author zhouxin
 * @since 2020/2/24
 */
public class SelectN {

    @SuppressWarnings("ComparatorCombinators")
    public static void main(String[] args) {
        int length = 10;
        int n = 4;
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(insertSort(array.clone(), 0, array.length - 1, (a, b) -> a - b)));
        System.out.println(select1(array.clone(), n, (a, b) -> b - a));
        System.out.println(select1(array.clone(), n, (a, b) -> a - b));
    }

    private static int select1(int[] array, int n, Comparator<Integer> comparator) {
        if (null == array || null == comparator) {
            throw new IllegalArgumentException("参数不能为空");
        } else if (array.length < 2) {
            return array[0];
        } else if (array.length < n || n < 1) {
            throw new IllegalArgumentException("选择数非法，不能小于1或大于数组涨肚");
        }
        int start = 0, end = array.length - 1;
        do {
            int head = start;
            int tag = end + 1;
            while (true) {
                while (comparator.compare(array[start], array[++head]) >= 0 && head < end) {
                }
                while (comparator.compare(array[start], array[--tag]) <= 0 && tag > start) {
                }
                if (head >= tag) {
                    break;
                }
                swap(array, head, tag);
            }
            swap(array, start, tag);
            if (tag == n - 1) {
                return array[tag];
            } else if (tag > n - 1) {
                end = tag - 1;
            } else {
                start = tag + 1;
            }
        } while (true);
    }

    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private static int[] insertSort(int[] array, int start, int end, Comparator<Integer> comparator) {
        if (end - start < 2 || array.length <= end) {
            return array;
        }
        for (int i = start + 1; i <= end; i++) {
            for (int j = i; j > start && comparator.compare(array[j - 1], array[j]) > 0; j--) {
                swap(array, j - 1, j);
            }
        }
        return array;
    }
}
