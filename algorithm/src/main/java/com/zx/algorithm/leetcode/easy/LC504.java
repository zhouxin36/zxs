package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/24
 */
public class LC504 {

    public static void main(String[] args) {
        System.out.println(new LC504().convertToBase7(100).equals("202"));
        System.out.println(new LC504().convertToBase7(-7).equals("-10"));
    }

    public String convertToBase7(int num) {
        if (num < 7 && num > -7) {
            return String.valueOf(num);
        }
        int res = 0;
        int ind = 1;
        while (Math.abs(num) > 0) {
            res += (num % 7) * ind;
            ind *= 10;
            num /= 7;
        }
        return Integer.toString(res);
    }

    public String convertToBase71(int num) {
        if (num < 7 && num > -7) {
            return String.valueOf(num);
        }
        boolean is = false;
        if (num < 0) {
            is = true;
            num = -num;
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (is) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}
