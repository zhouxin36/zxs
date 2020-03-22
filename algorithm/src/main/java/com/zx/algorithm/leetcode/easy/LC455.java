package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC455 {

    public static void main(String[] args) {
        System.out.println(new LC455().findContentChildren(new int[]{10,9,8,7}, new int[]{5,6,7,8}) == 2);
        System.out.println(new LC455().findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}) == 1);
        System.out.println(new LC455().findContentChildren(new int[]{1,2}, new int[]{1,2,3}) == 2);
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for (int i = 0, j = 0; j < g.length && i < s.length; i++) {
            if (g[j] <= s[i]){
                count++;
                j++;
            }
        }
        return count;
    }
}
