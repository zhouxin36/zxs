package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/17
 */
public class LC350 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC350().intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})).equals("[2, 2]"));
        System.out.println(Arrays.toString(new LC350().intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})).equals("[9, 9]"));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : nums1) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }
        int index = 0;
        for (int value : nums2) {
            if (map.containsKey(value)) {
                nums1[index++] = value;
                Integer remove = map.remove(value);
                if (remove != 1) {
                    map.put(value, remove - 1);
                }
            }
        }
        int[] arr = new int[index];
        System.arraycopy(nums1, 0, arr, 0, index);
        return arr;
    }
}
