package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class LC136 {

    public static void main(String[] args) {
        System.out.println(new LC136().singleNumber(new int[]{2, 2, 1}) == 1);
        System.out.println(new LC136().singleNumber(new int[]{4, 1, 2, 1, 2}) == 4);
    }

    public int singleNumber(int[] nums) {
        return method2(nums);
    }

    private int method1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums){
            Integer num = map.getOrDefault(i, 0);
            map.put(i, num + 1);
        }
        return map.keySet().stream().filter(e -> map.get(e) == 1).findFirst().orElse(0);
    }
    private int method2(int[] nums) {
        int result = 0;
        for (int i : nums){
            result ^= i;
        }
        return result;
    }

}
