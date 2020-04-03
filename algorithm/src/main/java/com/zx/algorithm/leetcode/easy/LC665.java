package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC665 {

    public static void main(String[] args) {
        System.out.println(!new LC665().checkPossibility(new int[]{3, 4, 2, 3}));
        System.out.println(!new LC665().checkPossibility(new int[]{4, 2, 1}));
        System.out.println(new LC665().checkPossibility(new int[]{4, 2, 3}));
    }

    public boolean checkPossibility(int[] nums) {
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {

            } else if (!flag) {
                flag = true;
                if (i - 2 >= 0 && i + 1 < nums.length && nums[i - 1] > nums[i + 1] && nums[i - 2] > nums[i]){
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
