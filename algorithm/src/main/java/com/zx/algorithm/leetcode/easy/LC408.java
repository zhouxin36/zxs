package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/20
 */
public class LC408 {

    public static void main(String[] args) {
        System.out.println(new LC408().validWordAbbreviation("internationalization", "i12iz4n"));
        System.out.println(!new LC408().validWordAbbreviation("apple", "a2e"));
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int num = 0;
        int wIndex = 0;
        for (int i = 0; i < abbr.length(); i++) {
            char c = abbr.charAt(i);
            if (c >= '0' && c <= '9'){
                num *= 10;
                num += (c - '0');
            }else {
                wIndex += num;
                if (c != word.charAt(wIndex)){
                    return false;
                }
                wIndex++;
                num = 0;
            }
        }
        return wIndex == word.length();
    }
}
