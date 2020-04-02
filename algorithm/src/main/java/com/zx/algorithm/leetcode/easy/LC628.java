package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/4/2
 */
public class LC628 {

    public static void main(String[] args) {
        System.out.println(new LC628().maximumProduct(new int[]{1, 2, 3}) == 6);
        System.out.println(new LC628().maximumProduct(new int[]{1, 2, 3, 4}) == 24);
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int max = 1;
        int max2 = Integer.MIN_VALUE;
        int count = 0;
        for (int i = nums.length - 1; i >= 0 && count < 3; i--) {
            max *= nums[i];
            count++;
        }
        if (nums.length > 3) {
            max2 = nums[0] * nums[1] * nums[nums.length - 1];
        }
        return Math.max(max, max2);
    }

    public int maximumProduct2(int[] nums) {
        if (nums.length < 3)
            return -1;
        int min1, min2, max1, max2, max3;
        min1 = min2 = Integer.MAX_VALUE;
        max1 = max2 = max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
