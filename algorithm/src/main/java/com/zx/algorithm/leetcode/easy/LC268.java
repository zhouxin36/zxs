package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/14
 */
public class LC268 {

    public static void main(String[] args) {
        System.out.println(new LC268().missingNumber(new int[]{3, 0, 1}) == 2);
        System.out.println(new LC268().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}) == 8);
    }

    /**
     * 两个数异或为零
     */
    public int missingNumber(int[] nums) {
        int a = 0;
        for (int i = 1; i <= nums.length; i++) {
            a ^= i;
            a ^= nums[i - 1];
        }
        return a;
    }
}
