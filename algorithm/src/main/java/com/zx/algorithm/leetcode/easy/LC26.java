package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC26 {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(new LC26().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        return removeDuplicates1(nums);
    }

    public int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return 1;
        }
        int index = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != num){
                nums[index] = nums[i];
                num = nums[i];
                index++;
            }
        }
        return index;
    }
}
