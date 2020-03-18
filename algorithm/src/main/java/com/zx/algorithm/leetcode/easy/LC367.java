package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/18
 */
public class LC367 {

    public static void main(String[] args) {
        System.out.println(new LC367().isPerfectSquare(16));
        System.out.println(!new LC367().isPerfectSquare(14));
    }

    /**
     * 牛顿迭代法
     */
    public boolean isPerfectSquare(int num) {
        double a = 1.0;
        double pref = 0.0;
        while (Math.abs(a - pref) > 0.5){
            pref = a;
            a = (a + num / a) / 2;
        }
        int re = (int) a;
        return Math.abs(re * re - num) < 0.1;
    }
}
