package com.zx.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouxin
 * @since 2020/3/11
 */
public class LC217 {

    public static void main(String[] args) {
        System.out.println(new LC217().containsDuplicate(new int[]{1, 2, 3, 1}));
        System.out.println(!new LC217().containsDuplicate(new int[]{1, 2, 3, 4}));
        System.out.println(new LC217().containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }
}
