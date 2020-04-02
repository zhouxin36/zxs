package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/2
 */
public class LC643 {

    public static void main(String[] args) {
        System.out.println(new LC643().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4) == 12.75);
    }

    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k) {
            return 0.0;
        }
        int sum = 0;
        int index = 0;
        double max = 0;
        for (; index < k; index++) {
            sum += nums[index];
        }
        max = sum;
        for (int i = index; i < nums.length; i++) {
            sum += nums[i];
            sum -= nums[i - k];
            if (max < sum){
                max = sum;
            }
        }
        return max * 1.0 / k;
    }
}
