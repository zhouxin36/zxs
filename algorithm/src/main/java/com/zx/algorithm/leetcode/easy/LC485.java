package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/23
 */
public class LC485 {

    public static void main(String[] args) {
        System.out.println(new LC485().findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}) == 3);
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int num = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1){
                num++;
            }else {
                max = Math.max(max, num);
                num = 0;
            }
        }
        return Math.max(max, num);
    }
}
