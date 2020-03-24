package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/24
 */
public class LC496 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC496().nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})).equals("[-1, 3, -1]"));
        System.out.println(Arrays.toString(new LC496().nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4})).equals("[3, -1]"));
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        return nextGreaterElement2(nums1, nums2);
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[nums1.length];
        for(int i=0;i<nums2.length;i++) {
            map.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            Integer index = map.get(nums1[i]);
            boolean is = false;
            for (int j = index; j < nums2.length; j++) {
                if (nums1[i] < nums2[j]){
                    arr[i] = nums2[j];
                    is = true;
                    break;
                }
            }
            if (!is){
                arr[i] = -1;
            }
        }
        return arr;
    }
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length];
        Arrays.fill(arr, -1);
        boolean is = false;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    is = true;
                }
                if (is && nums1[i] < nums2[j]){
                    arr[i] = nums2[j];
                    break;
                }
            }
            is = false;
        }
        return arr;
    }
}
