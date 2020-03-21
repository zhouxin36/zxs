package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC441 {
    public static void main(String[] args) {
        System.out.println(new LC441().arrangeCoins(1804289383) == 60070);
        System.out.println(new LC441().arrangeCoins(5) == 2);
        System.out.println(new LC441().arrangeCoins(8) == 3);
    }

    public int arrangeCoins(int n) {
        return arrangeCoins1(n);
    }
    /**
     * int n = x(x + 1) / 2;
     * 2*n = x(x + 1);
     * x2 + x - 2n = 0;
     * 1 + 4 * 2 * n ;
     */
    public int arrangeCoins1(int n) {
        double b = Math.sqrt(1 + 8.0 * n);
        return (int) ((b - 1) / 2);
    }
}
