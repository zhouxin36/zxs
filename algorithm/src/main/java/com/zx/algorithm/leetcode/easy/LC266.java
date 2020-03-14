package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/14
 */
public class LC266 {

    public static void main(String[] args) {
        System.out.println(!new LC266().canPermutePalindrome("code"));
        System.out.println(!new LC266().canPermutePalindrome("aab"));
        System.out.println(new LC266().canPermutePalindrome("carerac"));
    }
    public boolean canPermutePalindrome(String s) {
        return canPermutePalindrome1(s);
    }
    public boolean canPermutePalindrome1(String s) {
        for (int i = 0; i < s.length() >> 1; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
}
