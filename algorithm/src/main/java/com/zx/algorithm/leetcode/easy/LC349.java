package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouxin
 * @since 2020/3/17
 */
public class LC349 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC349().intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})).equals("[2]"));
        System.out.println(Arrays.toString(new LC349().intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})).equals("[9, 4]"));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return new int[]{};
            }
            Set<Integer> set = new HashSet<>();
            for (int value : nums1) {
                set.add(value);
            }
            int index = 0;
            for (int value : nums2) {
                if (set.contains(value)) {
                    nums1[index++] = value;
                    set.remove(value);
                }
            }
            int[] arr = new int[index];
            System.arraycopy(nums1, 0, arr, 0, index);
            return arr;
    }
}
