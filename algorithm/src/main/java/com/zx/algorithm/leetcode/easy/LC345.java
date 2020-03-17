package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/17
 */
public class LC345 {

    public static void main(String[] args) {
        System.out.println(new LC345().reverseVowels("12345").equals("12345"));
        System.out.println(new LC345().reverseVowels("hello").equals("holle"));
        System.out.println(new LC345().reverseVowels("leetcode").equals("leotcede"));
    }

    public String reverseVowels(String s) {
        if (s == null || s.length() < 2){
            return s;
        }
        int start = -1;
        int end = s.length();
        char[] chars = s.toCharArray();
        while (start < end) {
            while (!isVowels(chars[++start]) && start < s.length() - 1){}
            if (start + 1 == end){
                break;
            }
            while (!isVowels(chars[--end]) && end > 0){}
            if (start > end){
                break;
            }
            swap(chars, start, end);
        }
        return new String(chars);
    }

    private boolean isVowels(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

    private void swap(char[] s, int a, int b) {
        char ch = s[a];
        s[a] = s[b];
        s[b] = ch;
    }
}
