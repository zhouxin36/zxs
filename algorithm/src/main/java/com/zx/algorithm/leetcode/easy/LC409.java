package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/20
 */
public class LC409 {

    public static void main(String[] args) {
        System.out.println(new LC409().longestPalindrome("abccccdd") == 7);
    }

    public int longestPalindrome(String s) {
        int[] arr = new int[52];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                arr[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                arr[c - 'A' + 26]++;
            }
        }
        boolean hasOdd = false;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!hasOdd){
                if ((1 & arr[i]) == 0) {
                    sum += arr[i];
                }else {
                    hasOdd = true;
                    sum += (arr[i] & 0b11111111111111111111111111111110);
                }
            }else {
                sum += (arr[i] & 0b11111111111111111111111111111110);
            }
        }
        if (hasOdd){
            sum++;
        }
        return sum;
    }
}
