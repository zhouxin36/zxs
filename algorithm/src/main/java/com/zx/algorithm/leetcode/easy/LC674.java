package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC674 {

    public static void main(String[] args) {
        System.out.println(new LC674().findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}) == 3);
        System.out.println(new LC674().findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}) == 1);
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int max = 1;
        if (nums.length == 1){
            return max;
        }
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]){
                count++;
            }else {
                max = Math.max(max, count);
                count = 1;
            }
        }
        return Math.max(max, count);
    }
}
