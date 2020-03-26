package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/26
 */
public class LC532 {

    public static void main(String[] args) {
        System.out.println(new LC532().findPairs(new int[]{1, 1, 2, 2, 4}, 0) == 2);
        System.out.println(new LC532().findPairs(new int[]{1, 3, 1, 5, 4}, 0) == 1);
        System.out.println(new LC532().findPairs(new int[]{1, 2, 3, 4, 5}, -1) == 0);
        System.out.println(new LC532().findPairs(new int[]{1, 1, 1, 1, 1}, 0) == 1);
        System.out.println(new LC532().findPairs(new int[]{3, 1, 4, 1, 5}, 2) == 2);
        System.out.println(new LC532().findPairs(new int[]{1, 2, 3, 4, 5}, 1) == 4);
        System.out.println(new LC532().findPairs(new int[]{1, 3, 1, 5, 4}, 0) == 1);
    }

    public int findPairs(int[] nums, int k) {
        return findPairs2(nums, k);
    }

    public int findPairs2(int[] nums, int k) {
        if (k < 0){
            return 0;
        }
        Arrays.sort(nums);
        int a = 0, b = 1, count = 0;
        while (b < nums.length) {
            if (nums[b] - nums[a] < k || a == b) {
                b++;
            } else if (nums[b] - nums[a] > k) {
                a++;
            } else {
                count++;
                a++;
                b++;
                while (b < nums.length && nums[b - 1] == nums[b]){
                    b++;
                }
            }
        }
        return count;
    }


    public int findPairs1(int[] nums, int k) {
        if (nums.length < 2 || k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int count = 0;
        if (k == 0) {
            return (int) map.values().stream().filter(e -> e > 1).count();
        }
        Integer[] integers = map.keySet().toArray(new Integer[]{});
        Arrays.sort(integers);
        int a = 0, b = 1;
        while (b < integers.length) {
            if (integers[b] - integers[a] == k) {
                count++;
                a++;
                b++;
            } else if (integers[b] - integers[a] > k) {
                a++;
            } else {
                b++;
            }
        }
        return count;
    }
}
