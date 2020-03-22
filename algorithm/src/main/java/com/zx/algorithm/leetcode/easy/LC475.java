package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC475 {

    public static void main(String[] args) {
        System.out.println(new LC475().findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}) == 1);
        System.out.println(new LC475().findRadius(new int[]{1, 5}, new int[]{2}) == 3);
        System.out.println(new LC475().findRadius(new int[]{1, 2, 3}, new int[]{2}) == 1);
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0, res = 0;
        for (int house : houses) {
            // heaters[i] + heaters[i + 1] <= house * 2 房子偏向i+1
            while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2) {
                i++;
            }
            res = Math.max(res, Math.abs(heaters[i] - house));
        }
        return res;
    }
}
