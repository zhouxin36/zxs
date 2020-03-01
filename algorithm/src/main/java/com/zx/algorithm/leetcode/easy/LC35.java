package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC35 {

    public static void main(String[] args) {
        System.out.println(new LC35().searchInsert(new int[]{1, 3}, 1) == 0);
        System.out.println(new LC35().searchInsert(new int[]{1, 3, 5, 6}, 5) == 2);
        System.out.println(new LC35().searchInsert(new int[]{1, 3, 5, 6}, 2) == 1);
        System.out.println(new LC35().searchInsert(new int[]{1, 3, 5, 6}, 7) == 4);
        System.out.println(new LC35().searchInsert(new int[]{1, 3, 5, 6}, 0) == 0);
    }

    public int searchInsert(int[] nums, int target) {
        return searchInsert2(nums, target);
    }

    /**
     * 遍历求解
     */
    public int searchInsert1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (end > start + 1) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] < target) {
            return end + 1;
        } else {
            return end;
        }
    }
}
