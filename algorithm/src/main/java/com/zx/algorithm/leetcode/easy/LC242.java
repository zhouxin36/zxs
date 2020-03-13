package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/13
 */
public class LC242 {

    public static void main(String[] args) {
        System.out.println(new LC242().isAnagram("anagram", "nagaram"));
        System.out.println(!new LC242().isAnagram("rat", "car"));
    }

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int a : arr) {
            if (a != 0) {
                return false;
            }
        }
        return true;
    }
}
