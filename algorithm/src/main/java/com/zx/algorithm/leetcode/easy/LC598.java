package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/31
 */
public class LC598 {

    public static void main(String[] args) {
        System.out.println(new LC598().maxCount(3, 3, new int[][]{{2, 2}, {3, 3}}) == 4);
    }

    public int maxCount(int m, int n, int[][] ops) {
        int r = m, l = n;
        for (int i = 0; i < ops.length; i++) {
            r = Math.min(r, ops[i][0]);
            l = Math.min(l, ops[i][1]);
        }
        return r * l;
    }
}
