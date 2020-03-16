package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/16
 */
public class LC334 {
    public static void main(String[] args) {
//        char[] chars = new char[]{'h','e','l','l','o'};
        char[] chars = new char[]{'h','e','e','a','l','o'};
        new LC334().reverseString(chars);
        System.out.println(Arrays.toString(chars));
    }
    public void reverseString(char[] s) {
        int mid = s.length >> 1;
        for (int i = 0; i < mid; i++) {
            swap(s, i, s.length - 1 - i);
        }
    }

    private void swap(char[] s, int a, int b){
        char ch = s[a];
        s[a] = s[b];
        s[b] = ch;
    }
}
