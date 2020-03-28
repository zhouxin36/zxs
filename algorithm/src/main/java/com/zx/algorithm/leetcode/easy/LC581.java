package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC581 {

    public static void main(String[] args) {
        System.out.println(new LC581().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}) == 5);
    }

    public int findUnsortedSubarray(int[] nums) {
        return findUnsortedSubarray2(nums);
    }

    public int findUnsortedSubarray1(int[] nums) {
        int[] clone = nums.clone();
        int length = nums.length;
        Arrays.sort(clone);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (clone[i] == nums[i]) {
                count++;
            } else {
                break;
            }
        }
        if (count == nums.length) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (clone[length - i - 1] == nums[length - i - 1]) {
                count++;
            } else {
                break;
            }
        }
        return length - count;
    }

    public int findUnsortedSubarray2(int[] nums) {
        int remark = Integer.MAX_VALUE;
        boolean ch = false;
        int i = 1, count = 0;
        for (; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                remark = Math.min(remark, nums[i]);
                ch = true;
            }
        }
        if (!ch) {
            return 0;
        }
        for (int num : nums) {
            if (num > remark) {
                break;
            } else {
                count++;
            }
        }
        remark = Integer.MIN_VALUE;
        i = 0;
        for (; i < nums.length - 1; i++) {
            if (nums[nums.length - i - 2] > nums[nums.length - i - 1]) {
                remark = Math.max(remark, nums[nums.length - i - 2]);
            }
        }
        for (int k = 0; k < nums.length; k++) {
            if (nums[nums.length - k - 1] < remark) {
                break;
            } else {
                count++;
            }
        }
        return nums.length - count;
    }

}
