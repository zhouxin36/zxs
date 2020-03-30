package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/30
 */
public class LC594 {

    public static void main(String[] args) {
        System.out.println(new LC594().findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}) == 5);
    }

    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int max = 0;
        for (Integer integer : map.keySet()) {
            if (map.containsKey(integer + 1)) {
                max = Math.max(max, map.get(integer) + map.get(integer + 1));
            }
        }
        return max;
    }
}
