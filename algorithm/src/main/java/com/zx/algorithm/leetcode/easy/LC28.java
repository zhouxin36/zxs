package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC28 {
    public static void main(String[] args) {
        System.out.println(new LC28().strStr("a", "a") == 0);
        System.out.println(new LC28().strStr("aaa", "aaaa") == -1);
        System.out.println(new LC28().strStr("mississippi", "issipi") == -1);
        System.out.println(new LC28().strStr("hello", "ll") == 2);
        System.out.println(new LC28().strStr("aaaaa", "bba") == -1);
        System.out.println(new LC28().strStr("aaaaa", "") == 0);
    }

    public int strStr(String haystack, String needle) {
        return strStr1(haystack, needle);
    }

    /**
     * 暴力查询
     */
    public int strStr2(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        if (haystack == null || "".equals(haystack)) {
            return -1;
        }
        char first = needle.charAt(0);
        int max = (haystack.length() - needle.length());
        for (int i = 0; i <= max; i++) {
            if (haystack.charAt(i) != first) {
                while (++i <= max && haystack.charAt(i) != first);
            }
            if (i <= max) {
                int j = i + 1;
                int end = j + needle.length() - 1;
                for (int k = 1; j < end && haystack.charAt(j) == needle.charAt(k); j++, k++);
                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 暴力查询
     */
    public int strStr1(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        if (haystack == null || "".equals(haystack) || needle.length() > haystack.length()) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            for (; j < needle.length() && haystack.charAt(i + j) == needle.charAt(j); j++) {
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
