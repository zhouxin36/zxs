package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/19
 */
public class LC392 {

    public static void main(String[] args) {
        System.out.println(new LC392().isSubsequence("abc", "ahbgdc"));
        System.out.println(!new LC392().isSubsequence("axc", "ahbgdc"));
    }

    public boolean isSubsequence(String s, String t) {
        int index = 0;
        for (int i = 0; i < t.length() && index < s.length(); i++) {
            if (t.charAt(i) == s.charAt(index)){
                index++;
            }
        }
        return s.length() == index;
    }

}
