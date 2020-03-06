package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/6
 */
public class LC125 {

    public static void main(String[] args) {
        System.out.println(new LC125().isPalindrome("0P"));
        System.out.println(new LC125().isPalindrome(".,"));
        System.out.println(new LC125().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(!new LC125().isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() < 2){
            return true;
        }
        int head = -1;
        int tag = s.length();
        while (true){
            while (!isA(s.charAt(++head)) && head < s.length() - 1){}
            while (!isA(s.charAt(--tag)) && head != tag){}
            if (head >= tag){
                return true;
            }else if(!isEqual(s.charAt(head), s.charAt(tag))){
                return false;
            }
        }
    }

    public boolean isA(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || isB(ch);
    }

    public boolean isB(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public boolean isEqual(char ch1, char ch2){
        if (isB(ch1)){
            return ch1 == ch2;
        }
        return ch1 == ch2 || ch1 - ch2 == 32 || ch2 - ch1 == 32;
    }
}
