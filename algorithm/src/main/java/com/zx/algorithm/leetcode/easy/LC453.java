package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC453 {
    public static void main(String[] args) {
        System.out.println(new LC453().minMoves(new int[]{0}) == 0);
        System.out.println(new LC453().minMoves(new int[]{1, 1}) == 0);
        System.out.println(new LC453().minMoves(new int[]{0, 0}) == 0);
        System.out.println(new LC453().minMoves(new int[]{1, 2, 3}) == 3);
    }

    /**
     * 设最终值为x，数组长度l，最小值m，值a_1...a_n
     * (x - m)(l - 1) = ∑0_l{(x-a_i)} = x * l - ∑0_l{a_i}
     * x = ∑0_l(a_i) - ml + m
     * x - m = ∑0_l(a_i) - ml
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            min = Math.min(min, nums[i]);
        }
        return sum - min * nums.length;
    }

    /**
     * 设差值为x，数组长度l，最小值m，值a_1...a_n
     * x(l - 1) = ∑0_l{(k + m -a_i)} = x * l - ∑0_l{m - a_i}
     * x = ∑0_l{a_i - m}
     * 每个值到最小值的距离和
     */
    public int minMoves2(int[] nums) {
        if(nums.length == 0) return 0;
        int min = Integer.MAX_VALUE, sum = 0;
        for(int i : nums) min = Math.min(min, i);
        for(int i : nums){
            sum += (i - min);
        }
        return sum;
    }
}
