package com.zx.algorithm.leetcode.easy;

import java.util.stream.Stream;

/**
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC14 {

    public static void main(String[] args) {
        System.out.println(new LC14().longestCommonPrefix(new String[]{"flower", "flow", "flight"}).equals("fl"));
        System.out.println(new LC14().longestCommonPrefix(new String[]{"dog", "racecar", "car"}).equals(""));
    }

    public String longestCommonPrefix(String[] strs) {
        return longestCommonPrefix3(strs);
    }

    /**
     * 前缀一个一个匹配
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = strs.length;
        int minSize = Stream.of(strs).map(String::length).min(Integer::compareTo).orElse(0);
        int index = 0;
        for (int i = 0; i < minSize; i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j - 1].charAt(i) != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }

    /**
     * 字符串匹配前缀
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String pref = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(pref)) {
                pref = pref.substring(0, pref.length() - 1);
                if ("".equals(pref)) {
                    return "";
                }
            }
        }
        return pref;
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != ch) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
