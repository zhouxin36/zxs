package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/11
 */
public class LC219 {

    public static void main(String[] args) {
        System.out.println(new LC219().containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(new LC219().containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(!new LC219().containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer index = map.get(nums[i]);
                if (i - index <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
