package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/13
 */
public class LC246 {

    public static void main(String[] args) {
        System.out.println(new LC246().isStrobogrammatic("69"));
        System.out.println(new LC246().isStrobogrammatic("88"));
        System.out.println(new LC246().isStrobogrammatic("818"));
    }

    public boolean isStrobogrammatic(String num) {
        // 0, 1, 69, 8
        int len = num.length();
        if (len == 0) {
            return false;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            char l = num.charAt(left);
            char r = num.charAt(right);
            if (l == r) {
                if (l != '8' && l != '1' && l != '0') {
                    return false;
                }
            } else {
                if (!(l == '6' && r == '9') && !(l == '9' && r == '6')) {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }
}
