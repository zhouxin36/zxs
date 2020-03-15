package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC293 {

    public static void main(String[] args) {
        System.out.println(new LC293().generatePossibleNextMoves("++++"));
    }
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) {
            return res;
        }
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == '+' && arr[i - 1] == '+') {
                arr[i] = '-';
                arr[i - 1] = '-';
                res.add(String.valueOf(arr));
                arr[i] = '+';
                arr[i - 1] = '+';
            }
        }
        return res;
    }
}
