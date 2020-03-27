package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/27
 */
public class LC541 {
    public static void main(String[] args) {
        System.out.println(new LC541().reverseStr("abcdefgh", 10).equals("hgfedcba"));
        System.out.println(new LC541().reverseStr("abcdefgh", 6).equals("fedcbagh"));
        System.out.println(new LC541().reverseStr("abcdefgh", 3).equals("cbadefhg"));
        System.out.println(new LC541().reverseStr("abcdefg", 3).equals("cbadefg"));
        System.out.println(new LC541().reverseStr("abcdefg", 2).equals("bacdfeg"));
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            int end = Math.min(i + k - 1, chars.length - 1);
            for (int j = 0; j < (end - i + 1) / 2; j++) {
                chars[i + j] ^= chars[end - j];
                chars[end - j] ^= chars[i + j];
                chars[i + j] ^= chars[end - j];
            }
        }
        return new String(chars);
    }
}
