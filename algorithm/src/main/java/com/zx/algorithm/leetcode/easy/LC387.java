package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhouxin
 * @since 2020/3/19
 */
public class LC387 {

    public static void main(String[] args) {
        System.out.println(new LC387().firstUniqChar("leetcode") == 0);
        System.out.println(new LC387().firstUniqChar("loveleetcode") == 2);
    }

    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0){
            return -1;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            arr[ch]++;
        }
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            if (arr[ch] == 1){
                return i;
            }
        }
        return -1;
    }
}
