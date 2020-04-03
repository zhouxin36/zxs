package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC645 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC645().findErrorNums(new int[]{1, 2, 2, 4})).toString().equals("[2,3]"));
    }

    public int[] findErrorNums(int[] nums) {
        int[] tmp = new int[nums.length];
        int[] arr = new int[2];
        int a = 0;
        int b = 0;
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (tmp[nums[i] - 1] == 1){
                arr[0] = nums[i];
                flag = true;
            }else {
                if (!flag) {
                    tmp[nums[i] -1 ] = 1;
                }
                a ^= nums[i];
            }
            b ^= i + 1;
        }
        arr[1] = a ^ b;
        return arr;
    }
}
