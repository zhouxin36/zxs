package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/26
 */
public class LC521 {
    public static void main(String[] args) {
        System.out.println(new LC521().findLUSlength("aba", "cdc") == 3);
    }

    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}
