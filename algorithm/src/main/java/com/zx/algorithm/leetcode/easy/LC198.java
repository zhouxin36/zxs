package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/9
 */
public class LC198 {

    public static void main(String[] args) {
        System.out.println(new LC198().rob(new int[]{1, 2, 3, 1}) == 4);
        System.out.println(new LC198().rob(new int[]{2, 7, 9, 3, 1}) == 12);
    }

    /**
     * dp[i] = max(num[i] + dp[i - 2], dp[i - 1])
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int a, b;
        a = nums[0];
        b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int tmp = b;
            b = Math.max(a + nums[i], b);
            a = tmp;
        }
        return Math.max(a, b);
    }
}
