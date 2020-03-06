package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/6
 */
public class LC121 {

    public static void main(String[] args) {
        System.out.println(new LC121().maxProfit(new int[]{7, 1, 5, 3, 6, 4}) == 5);
        System.out.println(new LC121().maxProfit(new int[]{7, 6, 4, 3, 1}) == 0);
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2){
            return 0;
        }
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i : prices){
            min = Math.min(i, min);
            max = Math.max(max, i - min);
        }
        return max;
    }
}
