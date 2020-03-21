package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC434 {

    public static void main(String[] args) {
        System.out.println(new LC434().countSegments("  1  ") == 1);
        System.out.println(new LC434().countSegments("Hello, my name is John") == 5);
    }

    public int countSegments(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int segmentCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i-1) == ' ') && s.charAt(i) != ' ') {
                segmentCount++;
            }
        }
        return segmentCount;
    }
}
