package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/18
 */
public class LC383 {
    public static void main(String[] args) {
        System.out.println(new LC383().canConstruct("", ""));
        System.out.println(!new LC383().canConstruct("a", "b"));
        System.out.println(!new LC383().canConstruct("aa", "ab"));
        System.out.println(new LC383().canConstruct("aa", "aab"));
        System.out.println(new LC383().canConstruct("fffbfg", "effjfggbffjdgbjjhhdegh"));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        return canConstruct2(ransomNote, magazine);
    }

    public boolean canConstruct1(String ransomNote, String magazine) {
        if(ransomNote.equals(magazine)){
            return true;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (map.containsKey(c)){
                map.put(c, map.remove(c) + 1);
            }else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < magazine.length() && !map.isEmpty(); i++) {
            char c = magazine.charAt(i);
            Integer remove = map.remove(c);
            if (remove != null && remove != 1){
                map.put(c, remove - 1);
            }
        }
        return map.isEmpty();
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        int length = ransomNote.length();
        int[] arr = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            arr[c - 'a']++;
        }
        for (int i = 0; i < magazine.length() && length > 0; i++) {
            int  c = magazine.charAt(i) - 'a';
            if (arr[c] > 0){
                arr[c]--;
                length--;
            }
        }
        return length == 0;
    }

}
