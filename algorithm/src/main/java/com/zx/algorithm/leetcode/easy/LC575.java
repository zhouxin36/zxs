package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC575 {

    public static void main(String[] args) {
        System.out.println(new LC575().distributeCandies(new int[]{1, 1, 2, 2, 3, 3}) == 3);
        System.out.println(new LC575().distributeCandies(new int[]{1, 1, 2, 3}) == 2);
    }

    public int distributeCandies(int[] candies) {
        return distributeCandies1(candies);
    }

    public int distributeCandies1(int[] candies) {
        if (candies == null || candies.length == 0) {
            return 0;
        }
        Arrays.sort(candies);
        int count = 1;
        for (int i = 1; i < candies.length && count < candies.length / 2; i++) {
            if (candies[i - 1] != candies[i]) {
                count++;
            }
        }
        return count;
    }

    public int distributeCandies2(int[] candies) {
        if (candies == null || candies.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < candies.length; i++) {
            set.add(candies[i]);
        }
        return Math.min(set.size(), candies.length / 2);
    }
}
