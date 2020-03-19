package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/19
 */
public class LC389 {

    public static void main(String[] args) {
        System.out.println(new LC389().findTheDifference1("abcd", "abcde") == 'e');
    }

    public char findTheDifference(String s, String t) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (arr[c - 'a'] == 0){
                return c;
            }else {
                arr[c - 'a']--;
            }
        }
        return '0';
    }

    public char findTheDifference1(String s, String t) {
        int a = 0;
        for (int i = 0; i < s.length(); i++) {
            a ^= s.charAt(i);
            a ^= t.charAt(i);
        }
        a ^= t.charAt(t.length() - 1);
        return (char) a;
    }
}
