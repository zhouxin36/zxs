package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC561 {

    public static void main(String[] args) {
        System.out.println(new LC561().arrayPairSum(new int[]{1, 4, 3, 2}) == 4);
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int min = 0;
        for (int i = 0; i < nums.length; i += 2) {
            min += nums[i];
        }
        return min;
    }
}
