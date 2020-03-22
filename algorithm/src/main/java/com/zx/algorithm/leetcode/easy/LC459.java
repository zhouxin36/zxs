package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC459 {

    public static void main(String[] args) {
        System.out.println(!new LC459().repeatedSubstringPattern("abaabaa"));
        System.out.println(!new LC459().repeatedSubstringPattern("abcabcabcabcb"));
        System.out.println(new LC459().repeatedSubstringPattern("abab"));
        System.out.println(!new LC459().repeatedSubstringPattern("aba"));
        System.out.println(new LC459().repeatedSubstringPattern("abcabcabcabc"));
    }

    public boolean repeatedSubstringPattern(String str) {
        int[] next = new int[str.length() + 1];
        int i = 1;
        int j = 0;
        while (i < str.length()){
            while(j > 0 && str.charAt(i) != str.charAt(j)) j = next[j];
            if (str.charAt(i) == str.charAt(j)) j++;
            next[++i] = j;
        }
        int strSubLen = str.length() - next[str.length()];
//        if (strSubLen == str.length() || strSubLen > str.length() / 2) return false;
        if (strSubLen == str.length() || str.length() % strSubLen != 0) return false;
        for (i = strSubLen; i < str.length();){
            for (j = 0; j < strSubLen; i++, j++)
                if (i >= str.length() || str.charAt(i) != str.charAt(j))
                    return false;
        }
        return true;
    }
}
