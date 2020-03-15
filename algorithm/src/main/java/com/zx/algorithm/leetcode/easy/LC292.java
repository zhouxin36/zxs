package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC292 {
    public static void main(String[] args) {
        System.out.println(!new LC292().canWinNim(4));
    }

    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
}
