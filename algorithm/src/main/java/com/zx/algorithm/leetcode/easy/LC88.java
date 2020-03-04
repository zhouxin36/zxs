package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/3
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC88 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 0, 0, 0};
        new LC88().merge(arr, 3, new int[]{2, 5, 6}, 3);
        System.out.println(Arrays.toString(arr));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
                k--;
            } else {
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
