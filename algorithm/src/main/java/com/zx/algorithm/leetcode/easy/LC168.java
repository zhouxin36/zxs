package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class LC168 {

    public static void main(String[] args) {
        System.out.println(new LC168().convertToTitle(52).equals("AZ"));
        System.out.println(new LC168().convertToTitle(702).equals("ZZ"));
        System.out.println(new LC168().convertToTitle(28).equals("AB"));
        System.out.println(new LC168().convertToTitle(701).equals("ZY"));
        System.out.println(new LC168().convertToTitle(1).equals("A"));
    }

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int a = (--n) % 26;
            n /= 26;
            sb.append((char) ('A' + a));
        }
        return sb.reverse().toString();
    }
}
