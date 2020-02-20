package com.zx.algorithm.exp;

import java.util.ArrayList;
import java.util.List;

/**
 * ﻿给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * @author zhouxin
 * @since 2020/2/9
 */
public class SS4 {

    public static void main(String[] args) {
        Integer[] num1 = new Integer[]{1,2,2,1};
        Integer[] num2 = new Integer[]{2,2};
        System.out.println(method(num1, num2));

    }

    private static List<Integer> method(Integer[] num1, Integer[] num2) {
        HeapSort(num1);
        HeapSort(num2);
        List<Integer> list = new ArrayList<>();
        Integer prefNumber = null;
        for (int i = 0, j = 0; i < num1.length && j < num2.length;) {
            if(num1[i] > num2[j]){
                j++;
            }else if(num1[i] < num2[j]){
                i++;
            }else {
                if(prefNumber == null || !prefNumber.equals(num1[i])){
                    list.add(num1[i]);
                    prefNumber = num1[i];
                }
                i++;j++;
            }
        }
        return list;
    }


    private static void HeapSort(Integer[] nums) {
        int size = nums.length - 1;
        for (int i = size / 2 - 1; i >= 0; i--) {
            doHeapSort(nums, i, size);
        }
        while (size > 0){
            swap(nums, 0, size--);
            doHeapSort(nums, 0, size);
        }
    }

    private static void swap(Integer[] nums, int a, int b){
        Integer tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private static Integer compare(Integer[] nums, int a, int b){
        return nums[a] - nums[b];
    }

    private static void doHeapSort(Integer[] nums, int n, int size) {
        while (2 * n < size){
            int index = 2 * n + 1;
            if(index < size && compare(nums, index, index + 1) < 0){
                index++;
            }
            if(compare(nums, n, index) >= 0){
                return;
            }
            swap(nums, n, index);
            n = index;
        }
    }
}
