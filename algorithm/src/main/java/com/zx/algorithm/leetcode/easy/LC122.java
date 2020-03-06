package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/6
 */
public class LC122 {

    public static void main(String[] args) {
        System.out.println(new LC122().maxProfit(new int[]{7,1,5,3,6,4}) == 7);
        System.out.println(new LC122().maxProfit(new int[]{1,2,3,4,5}) == 4);
        System.out.println(new LC122().maxProfit(new int[]{7,6,4,3,1}) == 0);
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2){
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0){
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }
}
