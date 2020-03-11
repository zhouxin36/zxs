package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouxin
 * @since 2020/3/10
 */
public class LC205 {

    public static void main(String[] args) {
        System.out.println(!new LC205().isIsomorphic("ab", "aa"));
        System.out.println(new LC205().isIsomorphic("egg", "add"));
        System.out.println(new LC205().isIsomorphic("paper", "title"));
        System.out.println(!new LC205().isIsomorphic("foo", "bar"));
    }

    public boolean isIsomorphic1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> m = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (m.containsKey(s.charAt(i))) {
                if (t.charAt(i) != m.get(s.charAt(i)))
                    return false;
            } else if (set.contains(t.charAt(i))) {
                return false;
            } else {
                m.put(s.charAt(i), t.charAt(i));
                set.add(t.charAt(i));
            }
        }
        return true;
    }

    public boolean isIsomorphic(String str, String tstr) {
        int[] smap = new int[256];
        int[] tmap = new int[256];
        char[] s = str.toCharArray();
        char[] t = tstr.toCharArray();

        for (int i = 0; i < s.length; i++) {
            if (smap[s[i]] == 0 && tmap[t[i]] == 0) {
                smap[s[i]] = t[i];
                tmap[t[i]] = s[i];
            } else if (smap[s[i]] != t[i]) return false;
        }
        return true;
    }
}
