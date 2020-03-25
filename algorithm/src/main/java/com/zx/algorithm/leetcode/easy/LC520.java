package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/25
 */
public class LC520 {

    public static void main(String[] args) {
        System.out.println(new LC520().detectCapitalUse("U"));
        System.out.println(!new LC520().detectCapitalUse("sU"));
        System.out.println(new LC520().detectCapitalUse("Us"));
        System.out.println(new LC520().detectCapitalUse("USA"));
        System.out.println(new LC520().detectCapitalUse("Usa"));
        System.out.println(!new LC520().detectCapitalUse("UsA"));
        System.out.println(!new LC520().detectCapitalUse("FlaG"));
    }

    public boolean detectCapitalUse(String word) {
        char c = word.charAt(0);
        boolean firstC = c >= 'A' && c <= 'Z';
        if (word.length() < 2){
            return true;
        }
        c = word.charAt(1);
        boolean secondC = c >= 'A' && c <= 'Z';
        if (!firstC && secondC){
            return false;
        }
        for (int i = 2; i < word.length(); i++) {
            c = word.charAt(i);
            boolean tC = c >= 'A' && c <= 'Z';
            if (tC != secondC){
                return false;
            }
        }
        return true;
    }
}
