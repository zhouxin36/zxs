package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC283 {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 3, 12};
        new LC283().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr).equals("[1, 3, 12, 0, 0]"));
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        int zIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                zIndex = i;
                break;
            }
        }
        if (zIndex == -1){
            return;
        }
        for (int i = zIndex + 1; i < nums.length; i++) {
            if (nums[i] != 0){
                swap(nums, zIndex, i);
                zIndex++;
            }
        }
    }

    public void swap(int[] nums, int a, int b) {
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }
}
