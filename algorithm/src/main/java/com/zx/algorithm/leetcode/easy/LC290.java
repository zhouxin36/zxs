package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC290 {

    public static void main(String[] args) {
        System.out.println(new LC290().wordPattern("abba", "dog cat cat dog"));
        System.out.println(!new LC290().wordPattern("abba", "dog cat cat fish"));
        System.out.println(!new LC290().wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(!new LC290().wordPattern("abba", "dog dog dog dog"));
    }

    public boolean wordPattern(String pattern, String str) {
        return wordPattern1(pattern, str);
    }

    public boolean wordPattern1(String pattern, String str) {
        String[] s = str.split(" ");
        if (pattern.length() != s.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                String s1 = map.get(c);
                if (!s1.equals(s[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(s[i])){
                    return false;
                }
                map.put(c, s[i]);
            }
        }
        return true;
    }
}
