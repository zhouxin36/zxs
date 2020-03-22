package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC461 {

    public static void main(String[] args) {
        System.out.println(new LC461().hammingDistance(1, 4) == 2);
    }

    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
