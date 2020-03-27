package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/27
 */
public class LC557 {
    public static void main(String[] args) {
        System.out.println(new LC557().reverseWords("Let's take LeetCode contest").equals("s'teL ekat edoCteeL tsetnoc"));
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        char[] chars = s.toCharArray();
        int start = 0, end = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' '){
                for (int j = 0; j <= (end - start) / 2; j++) {
                    char tmp = chars[start + j];
                    chars[start + j] =  chars[end - j];
                    chars[end - j] = tmp;
                }
                start = i + 1;
            }else {
                end = i;
            }
        }
        for (int j = 0; j <= (end - start) / 2; j++) {
            char tmp = chars[start + j];
            chars[start + j] =  chars[end - j];
            chars[end - j] = tmp;
        }
        return new String(chars);
    }
}
