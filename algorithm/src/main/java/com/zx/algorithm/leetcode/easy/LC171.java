package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/8
 */
public class LC171 {

    public static void main(String[] args) {
        System.out.println(new LC171().titleToNumber("A") == 1);
        System.out.println(new LC171().titleToNumber("AB") == 28);
        System.out.println(new LC171().titleToNumber("ZY") == 701);
    }

    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sum = sum * 26 + c - 'A' + 1;
        }
        return sum;
    }
}
