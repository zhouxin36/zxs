package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/2/29
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC9 {
    public static void main(String[] args) {
        System.out.println(new LC9().isPalindrome(10));
        System.out.println(new LC9().isPalindrome(121));
        System.out.println(new LC9().isPalindrome(221));
        System.out.println(new LC9().isPalindrome(222));
        System.out.println(new LC9().isPalindrome(2332));
        System.out.println(new LC9().isPalindrome(2233));
        System.out.println(new LC9().isPalindrome(-222));
    }

    public boolean isPalindrome(int x) {
        return isPalindrome2(x);
    }

    /**
     * 子串比对
     */
    public boolean isPalindrome1(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }
        if ( x < 0 || x % 10 == 0){
            return false;
        }
        String str = x + "";
        int length = str.length() - 1;
        for (int i = 0; i < (length >> 1) + 1; i++) {
            if (str.charAt(i) != str.charAt(length - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }
        if ( x < 0 || x % 10 == 0){
            return false;
        }
        int revert = 0;
        while (x > revert){
            revert = revert * 10 + x % 10;
            x /= 10;
        }
        return x == revert || x == revert / 10;
    }
}
